package whatever.headfirst.domain.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import whatever.headfirst.domain.auth.exception.KakaoTokenException;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.member.dto.MemberLoginRequest;
import whatever.headfirst.domain.member.repository.MemberRepository;
import whatever.headfirst.domain.token.dto.TokenResponse;
import whatever.headfirst.global.jwt.TokenProvider;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public TokenResponse login(MemberLoginRequest request) {
        final String KAKAO_URL = "https://kapi.kakao.com/v2/user/me";

        ResponseEntity<String> userInfo = accessKakao(request, KAKAO_URL);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(userInfo.getBody());
        } catch (JsonProcessingException e) {
            throw new KakaoTokenException();
        }
        Long uuid = Long.valueOf(extractKakaoInfo(jsonNode, "id"));
        String nickname = extractKakaoInfo(jsonNode, "properties", "nickname");
        String email = extractKakaoInfo(jsonNode, "kakao_account", "email");

        Member member = memberRepository.findByUuid(uuid).orElseGet(() -> createMember(uuid, email, nickname));
        String token = tokenProvider.createToken(member);

        return TokenResponse.from(token);
    }

    private static ResponseEntity<String> accessKakao(MemberLoginRequest request, String url) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        setHeaderProperties(request, headers);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> userInfo = template.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return userInfo;
    }

    private static void setHeaderProperties(MemberLoginRequest request, HttpHeaders headers) {
        final String AUTHORIZATION = "Authorization";
        final String BEARER = "Bearer ";

        headers.set(AUTHORIZATION, BEARER + request.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private static String extractKakaoInfo(JsonNode jsonNode, String key) {
        return jsonNode.get(key).asText();
    }

    private static String extractKakaoInfo(JsonNode jsonNode, String key, String value) {
        return jsonNode.get(key).get(value).asText();
    }

    @Transactional
    public Member createMember(Long uuid, String email, String nickname) {
        System.out.println("email = " + email);
        Member createMember = Member.from(uuid, email, nickname);
        return memberRepository.save(createMember);
    }
}