package com.Project.QuizApp.Service;

import com.Project.QuizApp.Dao.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuestionDao questiondao;
    @Autowired
    QuizDao quizdao;

    @Transactional
    public ResponseEntity<String> createRandomQuiz(String category, String title, int noQues) {
        List<Question> questions = questiondao.createRandomQuiz(category, noQues);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizdao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionToUser = new ArrayList<>();

        for (Question q : questionFromDB) {
            QuestionWrapper wrapper = new QuestionWrapper(q.getId(), q.getQuestion_text(), q.getOption_1(), q.getOption_2(), q.getOption_3(), q.getOption_4());
            questionToUser.add(wrapper);
        }
        return new ResponseEntity<>(questionToUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> getResult(int id, List<Response> responses) {
        Quiz quiz = quizdao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int i = 0, result = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
                result++;
            }
            i++;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

