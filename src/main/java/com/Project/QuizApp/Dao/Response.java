package com.Project.QuizApp.Dao;

import lombok.Data;

@Data
public class Response {

    private int id;
    private String Response;

    public Response(int id, String response) {
        this.id = id;
        Response = response;
    }
}
