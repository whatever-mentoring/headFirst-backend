package whatever.headfirst.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.member.exception.MemberNotFoundException;
import whatever.headfirst.domain.member.repository.MemberRepository;
import whatever.headfirst.global.holder.MemberContextHolder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String nickname = user.getUsername();
            Member targetMember = memberRepository.findByNickname(nickname)
                    .orElseThrow(MemberNotFoundException::new);
            MemberContextHolder.setMember(targetMember);
            log.info("Security Context에 '{}' 인증 정보 저장, uri = {}", authentication.getName(), requestURI);
        } else {
            log.info(jwt);
            log.info("유효한 JWT 토큰 없음, uri = {}", requestURI);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}