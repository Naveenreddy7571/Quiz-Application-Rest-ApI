package com.Project.QuizApp.Dao; //only talks with databases and contains data

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {


    List<Question> findBycategory(String category);
    @Query(value ="SELECT * FROM quiz_questions q WHERE q.category=:category ORDER BY RAND() LIMIT :noQues" ,nativeQuery = true)
    List<Question> createRandomQuiz(String category,int noQues);
}
