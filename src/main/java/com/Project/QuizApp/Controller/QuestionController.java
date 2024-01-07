package com.Project.QuizApp.Controller;

import com.Project.QuizApp.Dao.Question;
import com.Project.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService Questionservice;
    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> AllQuestions()
    {
        return Questionservice.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> findByCategory(@PathVariable String category)
    {
        return Questionservice.finByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> AddQuestion(@RequestBody Question question)
    {
        return Questionservice.AddQuestion(question);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> DeleteQuestion(@RequestParam int id)
    {
        return Questionservice.DeleteQuestion(id);
    }

    @PostMapping("/update")
    public ResponseEntity<String> UpdateQuestion(@RequestBody int id, String Question)
    {
        return Questionservice.UpdateQuestion(id,Question);
    }

}
