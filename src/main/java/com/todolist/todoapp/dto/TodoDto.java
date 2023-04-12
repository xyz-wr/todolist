package com.todolist.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private Long todoOrder;
        private Boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        @Setter
        private Long todoId;

        private String title;

        private Long todoOrder;

        private Boolean completed;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long todoId;
        private String title;
        private Long todoOrder;
        private Boolean completed;
    }
}
