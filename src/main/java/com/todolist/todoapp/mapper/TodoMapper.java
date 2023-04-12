package com.todolist.todoapp.mapper;


import com.todolist.todoapp.dto.TodoDto;
import com.todolist.todoapp.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoDto.Post postDto);
    Todo todoPatchDtoToTodo(TodoDto.Patch patchDto);
    TodoDto.Response todoToTodoResponseDto(Todo todo);
    List<TodoDto.Response> todoToTodoResponseDtos(List<Todo> todos);
}
