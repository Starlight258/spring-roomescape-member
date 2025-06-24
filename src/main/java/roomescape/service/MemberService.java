package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.domain.member.Member;
import roomescape.dto.request.member.SignupRequest;
import roomescape.dto.response.member.SignupResponse;
import roomescape.exception.ConflictException;
import roomescape.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public SignupResponse signup(final SignupRequest request) {
        String name = request.name();
        String email = request.email();
        validateDistinctName(name);
        validateDistinctEmail(email);
        Member savedMember = memberRepository.save(new Member(name, email, request.password()));
        return SignupResponse.from(savedMember);
    }

    public void login() {

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
}
