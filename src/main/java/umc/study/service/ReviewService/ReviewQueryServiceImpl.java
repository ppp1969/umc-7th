package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Optional<Member> member = memberRepository.findById(storeId);
        // pageRequest객체를 생성. 몇번 페이지에서 10개
        // page의 range를 프론트에서 1~N+1으로 주는걸, 0~N으로 변경
        return reviewRepository.findAllByMember(member.get(), PageRequest.of(page-1, 10));
    }
}
