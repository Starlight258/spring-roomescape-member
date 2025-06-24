package roomescape.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import roomescape.domain.member.Member;
import roomescape.dto.request.member.LoginRequest;
import roomescape.dto.request.member.SignupRequest;
import roomescape.dto.response.member.CheckLoginResponse;
import roomescape.dto.response.member.SignupResponse;
import roomescape.exception.ConflictException;
import roomescape.exception.RoomescapeException;
import roomescape.exception.UnAuthorizedException;
import roomescape.repository.MemberRepository;

@Service
public class MemberService {

    private static final String TOKEN = "token";

    private final MemberRepository memberRepository;
    private final CookieGenerator cookieGenerator;

    public MemberService(final MemberRepository memberRepository, final CookieGenerator cookieGenerator) {
        this.memberRepository = memberRepository;
        this.cookieGenerator = cookieGenerator;
    }

    public SignupResponse signup(final SignupRequest request) {
        String name = request.name();
        String email = request.email();
        validateDistinctName(name);
        validateDistinctEmail(email);
        Member savedMember = memberRepository.save(new Member(name, email, request.password()));
        return SignupResponse.from(savedMember);
    }

    public ResponseCookie login(final LoginRequest request, final HttpSession httpSession) {
        String email = request.email();
        if (!memberRepository.existsByEmail(email)) {
            throw new UnAuthorizedException("Member email does not exist");
        }
        if (!memberRepository.existsByEmailAndPassword(email, request.password())) {
            throw new UnAuthorizedException("Member password does not matched");
        }
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RoomescapeException("Server state cannot be reached"));
        Long memberId = member.getId();
        httpSession.setAttribute(TOKEN, memberId);
        return cookieGenerator.makeCookie(TOKEN, memberId.toString());
    }

    public CheckLoginResponse checkLogin(final HttpSession httpSession) {
        Long memberId = getMemberId(httpSession);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RoomescapeException("Server state cannot be reached"));
        return CheckLoginResponse.from(member);
    }

    private void validateDistinctName(final String name) {
        if (memberRepository.existsByName(name)) {
            throw new ConflictException("Member name is already exist");
        }
    }

    private void validateDistinctEmail(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new ConflictException("Member email is already exist");
        }
    }

    private Long getMemberId(final HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute(TOKEN);
        if (memberId == null || !memberRepository.existsById(memberId)) {
            throw new UnAuthorizedException("You are not logged in");
        }
        return memberId;
    }
}
