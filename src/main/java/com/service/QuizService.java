
package com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.exception.ResourceNotFoundException;
import com.repository.QuizRepository;
import com.repository.SubjectRepository;
import com.config.QuizConfig;
import com.entity.Question;
import com.entity.Quiz;
import com.entity.Subject;
import com.entity.User;
import com.repository.QuestionRepository;

import java.util.List;

@Service
public class QuizService {

    private SubjectRepository subjectRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    private QuizConfig quizConfig;

    @Autowired
    public QuizService(SubjectRepository subjectRepository, QuizRepository quizRepository,
                       QuestionRepository questionRepository, QuizConfig quizConfig) {
        this.subjectRepository = subjectRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.quizConfig = quizConfig;
    }

    public Quiz getQuiz(String subjectCode) {
        Subject subject = subjectRepository.getSubjectBySubjectCode(subjectCode);

        if (subject == null) {
            throw new ResourceNotFoundException("Following resource is not available at the moment");
        }

        List<Question> questions = questionRepository.findRandomQuestionsBySubjectAndSize(
                subject.getId(), quizConfig.getTotalQuestions());

        if (questions.size() < quizConfig.getTotalQuestions()) {
            throw new ResourceNotFoundException("Following resource is not available at the moment");
        }

        Quiz quiz = new Quiz();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setUser(user);
        quiz.setSubject(subject);
        quiz.setQuestions(questions);

        return quiz;
    }

    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public List<Quiz> getQuizzesByUser(User user) {
        return quizRepository.getByUser(user, Sort.by("instant").descending());
    }
}
