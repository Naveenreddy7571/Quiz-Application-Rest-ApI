package com.Project.QuizApp.Dao;

import jakarta.persistence.*;
import lombok.Data;

@Data //lombok internally generate all the getters and setters
@Entity
@Table(name="quiz_questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String DifficultyLevel;
    private String Question_text;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private String CorrectAnswer;
}
