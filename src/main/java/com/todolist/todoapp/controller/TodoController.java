package com.todolist.todoapp.controller;

import com.todolist.todoapp.dto.TodoDto;
import com.todolist.todoapp.entity.Todo;
import com.todolist.todoapp.mapper.TodoMapper;
import com.todolist.todoapp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/todos")
@Validated
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post postDto) {
        Todo todo = todoService.createTodo(mapper.todoPostDtoToTodo(postDto));

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(todo), HttpStatus.CREATED);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @Valid @RequestBody TodoDto.Patch patchDto) {

        patchDto.setTodoId(todoId);

        Todo response = todoService.updateTodo(mapper.todoPatchDtoToTodo(patchDto));


        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId) {
        Todo response = todoService.findTodo(todoId);

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();

        List<TodoDto.Response> response = mapper.todoToTodoResponseDtos(todos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @GetMapping
//    public ResponseEntity getTodos(@Positive @RequestParam int page,
//                                   @Positive @RequestParam int size) {
//        Page<Todo> todoPage = todoService.findTodos(page -1 , size);
//        List<Todo> todos = todoPage.getContent();
//
//        return new ResponseEntity<>(new MultiResponseDto<>(mapper.todoToTodoResponseDtos(todos),todoPage),
//                HttpStatus.OK);
//    }



    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
