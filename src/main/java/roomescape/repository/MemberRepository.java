package roomescape.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roomescape.domain.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByName(@NotBlank String name);

    boolean existsByEmail(@NotBlank String email);
}
