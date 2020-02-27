
package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
