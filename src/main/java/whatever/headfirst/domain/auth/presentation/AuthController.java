package whatever.headfirst.domain.auth.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatever.headfirst.domain.auth.application.AuthService;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.member.dto.MemberLoginRequest;
import whatever.headfirst.domain.member.dto.MemberRequest;
import whatever.headfirst.domain.member.dto.MemberResponse;
import whatever.headfirst.domain.token.dto.TokenResponse;
import whatever.headfirst.global.payload.ApiSuccessResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiSuccessResponse<MemberResponse>> join(@RequestBody @Valid MemberRequest request) {
        Member resultMember = authService.signup(request);
        return ApiSuccessResponse.result(HttpStatus.OK, resultMember.toDto());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<TokenResponse>> login(@RequestBody @Valid MemberLoginRequest request) {
        TokenResponse newToken = authService.login(request);
        return ApiSuccessResponse.result(HttpStatus.CREATED, newToken);
    }
}
