package umc.study.service.UserService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.User;

import java.util.Optional;

public interface UserQueryService {

    Optional<User> findUser(Long id);

    Page<Review> getReviewList(Long userId, Integer page);

    boolean existUser(Long userId);
}
