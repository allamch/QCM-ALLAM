
package com.repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Quiz;
import com.entity.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> getByUser(User user, Sort sort);
}
