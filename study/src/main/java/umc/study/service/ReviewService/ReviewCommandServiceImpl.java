package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.ReviewRequestDTO;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;


    @Override
    public Review joinReview(ReviewRequestDTO.JoinReviewDto request, Long storeId, Long userId) {
        Review newReview = ReviewConverter.toReview(request);

        // Store 얻기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeId));

        // User 얻기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + storeId));

        // newStore에 region 설정
        newReview.setStore(store);
        newReview.setUser(user);

        return reviewRepository.save(newReview);
    }
}
