package com.example.myapplication.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.room.bean.Student;

import java.util.List;

@Dao
public interface StudentDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student... students);

    @Delete
    void delete(Student... students);

    @Query("delete from Student")
    void deleteAll();

    @Update
    void update(Student... students);

    @Query("select * from Student")
    LiveData<List<Student>> queryAll();
}
