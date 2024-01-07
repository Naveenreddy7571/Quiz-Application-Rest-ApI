package com.Project.QuizApp.Service; //service class only do processing


import com.Project.QuizApp.Dao.QuestionDao;
import com.Project.QuizApp.Dao.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {

   @Autowired
    QuestionDao questiondao;

    public  ResponseEntity<String> AddQuestion(Question question) {
        try
        {
            questiondao.save(question);
            return new ResponseEntity<String>("Success",HttpStatus.OK);
        }
        catch(DataAccessException e)
        {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }

    public  ResponseEntity<List<Question>> getAllQuestions() {
        try
        {

            return new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK);
        }
        catch (DataAccessException exception)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<List<Question>> finByCategory(String category) {
        try
        {

            return new ResponseEntity<>(questiondao.findBycategory(category),HttpStatus.OK);
        }
        catch(DataAccessException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> DeleteQuestion(int id) {
        Question question = null;
        try {
            question = questiondao.findById(id).orElse(null);
        }
        catch(DataAccessException e) {
              return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        assert question != null;
        questiondao.delete(question);
            return new ResponseEntity<String>("successfully Deleted",HttpStatus.OK);

    }

    public ResponseEntity<String> UpdateQuestion(int id, String question) {
        Question obj=null;
        try
        {
             obj = questiondao.findById(id).orElse(null);
        }
        catch (DataAccessException e)
        {
            return new ResponseEntity<>("Id not found" , HttpStatus.NOT_FOUND);
        }
        assert obj != null;
        obj.setQuestion_text(question);
        questiondao.save(obj);
        return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);

    }
}
