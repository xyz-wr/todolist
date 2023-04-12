package com.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    TODO_NOT_FOUND(404, "Todo List Not Found");

    private int status;
    private String message;
}
