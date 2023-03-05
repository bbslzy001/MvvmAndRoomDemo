package com.example.myapplication.ui.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.room.bean.Student;
import com.example.myapplication.model.room.database.AppDatabase;

import java.util.List;

public class LiveDataViewModel extends ViewModel
{
    private final MutableLiveData<String> liveData = new MutableLiveData<>();

//    private final LiveData<List<Student>> liveDataFromRoom;
//
//    public LiveDataViewModel(@NonNull Context context)
//    {
//        AppDatabase appDatabase = AppDatabase.getAppDatabaseInstance(context);
//        // 初始化liveDataFromRoom变量
//        liveDataFromRoom = appDatabase.getStudentDao().queryAll();
//    }

    public MutableLiveData<String> getLiveData()
    {
        return liveData;
    }

//    public LiveData<List<Student>> getLiveDataFromRoom()
//    {
//        return liveDataFromRoom;
//    }
}
