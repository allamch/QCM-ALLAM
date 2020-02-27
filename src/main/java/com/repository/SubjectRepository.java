package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Subject;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject getSubjectBySubjectCode(String subjectCode);
}
