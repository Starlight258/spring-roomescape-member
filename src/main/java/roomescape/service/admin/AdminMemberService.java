package roomescape.service.admin;

import org.springframework.stereotype.Service;
import roomescape.domain.member.Member;
import roomescape.domain.member.MemberName;
import roomescape.domain.member.MemberRole;
import roomescape.dto.request.member.SignupRequest;
import roomescape.dto.response.member.SignupResponse;
import roomescape.exception.ConflictException;
import roomescape.repository.MemberRepository;

@Service
public class AdminMemberService {

    private final MemberRepository memberRepository;

    public AdminMemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public SignupResponse signup(final SignupRequest request) {
        MemberName memberName = new MemberName(request.name());
        String email = request.email();
        validateDistinctName(memberName);
        validateDistinctEmail(email);
        Member savedMember = memberRepository.save(new Member(memberName, email, request.password(), MemberRole.ADMIN));
        return SignupResponse.from(savedMember);
    }

    private void validateDistinctName(final MemberName name) {
        if (memberRepository.existsByName(name)) {
            throw new ConflictException("Member name is already exist");
        }
    }

    private void validateDistinctEmail(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new ConflictException("Member email is already exist");
        }
    }
}
