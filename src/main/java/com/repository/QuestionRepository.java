
package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.entity.Question;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM question q WHERE q.subject = :subjectId ORDER BY RAND() LIMIT :total")
    List<Question> findRandomQuestionsBySubjectAndSize(
            @Param("subjectId") Long subjectId,
            @Param("total") Integer total);
}
