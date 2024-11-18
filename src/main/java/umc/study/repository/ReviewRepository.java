package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰에 대한 페이징 객체
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);

}
