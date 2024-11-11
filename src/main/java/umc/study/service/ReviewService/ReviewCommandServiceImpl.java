package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final Long hardCodedMemberId = 1L; // 하드코딩된 유저 ID
    @Override
    @Transactional
    public Review createRevice(Long storeId, ReviewRequestDTO.CreateReviewDto request) {
        // Store 검증
        Optional<Store> store = storeRepository.findById(storeId);

        // Member 생성
        Optional<Member> member = memberRepository.findById(hardCodedMemberId);

        Review review = ReviewConverter.toReview(request);
        review.setStore(store.get());
        review.setMember(member.get());

        // 양방향 관계 설정
        review.setStore(store.get());
        review.setMember(member.get());

        return reviewRepository.save(review);
    }
}
