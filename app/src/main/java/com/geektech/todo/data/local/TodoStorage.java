package com.geektech.todo.data.local;

import com.geektech.todo.model.TodoAction;

import java.util.List;

public class TodoStorage {

    private TodoDao dao;

    public TodoStorage(TodoDao dao) {
        this.dao = dao;
    }

    public void saveTodoAction(TodoAction todoAction){
        dao.insert(todoAction);
    }

    public TodoAction getTodoAction(String key){
        return dao.get(key);
    }

    public List<TodoAction> getAllActions(){
        return dao.getAll();
    }

    public void deleteTodoAction(TodoAction todoAction){
        dao.delete(todoAction);
    }


}
