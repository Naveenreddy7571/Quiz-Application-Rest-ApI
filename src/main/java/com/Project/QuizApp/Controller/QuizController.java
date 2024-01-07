package com.Project.QuizApp.Controller;

import com.Project.QuizApp.Dao.QuestionWrapper;
import com.Project.QuizApp.Dao.Response;
import com.Project.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam String title, @RequestParam int noQues)
    {
        return quizService.createRandomQuiz(category,title,noQues);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
    {
            return quizService.getQuizQuestions(id);
    }

    @PostMapping("/result/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable int id, @RequestBody List<Response> responses)
    {
        return quizService.getResult(id,responses);
    }
}
