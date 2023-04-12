package com.todolist.todoapp.service;

import com.todolist.exception.BusinessLogicException;
import com.todolist.exception.ExceptionCode;
import com.todolist.todoapp.entity.Todo;
import com.todolist.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        if (todo.isCompleted()) {
            todo.setCompleted(true);
        }

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(order -> findTodo.setTodoOrder(order));

        if (!todo.isCompleted()) {
            findTodo.setCompleted(false);
        }

        if (todo.isCompleted()) {
            findTodo.setCompleted(true);
        }


        return todoRepository.save(findTodo);
    }

    public Todo findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

//    public Page<Todo> findTodos(int page, int size) {
//        return todoRepository.findAll(PageRequest.of(
//                page, size, Sort.by("todoId").ascending()));
//    }

    public void deleteTodo(long todoId) {
        Todo findTodo = findVerifiedTodo(todoId);

        todoRepository.delete(findTodo);
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    public Todo findVerifiedTodo(long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        Todo findTodo = optionalTodo.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }
}
