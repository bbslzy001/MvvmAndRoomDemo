package com.example.myapplication.model.repository;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model.room.bean.Student;
import com.example.myapplication.model.room.dao.StudentDao;
import com.example.myapplication.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRepository
{
    private final StudentDao studentDao;
    private final ExecutorService executor;

    public StudentRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        studentDao = myDatabase.getStudentDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Student... students)
    {
        executor.execute(() -> studentDao.insert(students));
    }

    public void delete(Student... students)
    {
        executor.execute(() -> studentDao.delete(students));
    }

    public void update(Student... students)
    {
        executor.execute(() -> studentDao.update(students));
    }

    public LiveData<List<Student>> queryAll()
    {
        return studentDao.queryAll();
    }
}
