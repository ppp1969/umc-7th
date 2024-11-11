package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    // 이름으로 사용자 찾기
    Optional<Region> findByName(String name);
}
