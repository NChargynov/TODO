package com.geektech.todo.data.local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geektech.todo.model.TodoAction;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodoAction todoAction);

    @Delete
    void delete(TodoAction todoAction);

    @Query("SELECT * FROM todo_action WHERE `key` =:key")
    TodoAction get(String key);


    @Query("SELECT * FROM todo_action")
    List<TodoAction> getAll();


}
