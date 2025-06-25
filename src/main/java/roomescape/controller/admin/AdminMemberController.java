package roomescape.controller.admin;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dto.request.member.SignupRequest;
import roomescape.dto.response.member.SignupResponse;
import roomescape.service.admin.AdminMemberService;

@RestController
@RequestMapping("/admin/members")
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    public AdminMemberController(final AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    @PostMapping
    public ResponseEntity<SignupResponse> signup(final @RequestBody @Valid SignupRequest request) {
        SignupResponse response = adminMemberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
