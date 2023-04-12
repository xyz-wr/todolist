package com.todolist.todoapp.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoResponseDto {
    private long todoId;
    private String title;
    private long todoOrder;
    private boolean completed;
}
