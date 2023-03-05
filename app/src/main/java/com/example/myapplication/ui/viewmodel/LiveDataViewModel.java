package com.example.myapplication.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.room.bean.Student;
import com.example.myapplication.model.room.database.AppDatabase;

import java.util.List;

/**
 * 采用可以接受 Application 参数的 AndroidViewModel 作为父类
 */
public class LiveDataViewModel extends AndroidViewModel
{
    private final MutableLiveData<String> liveData = new MutableLiveData<>();

    private final LiveData<List<Student>> liveDataFromRoom;

    public LiveDataViewModel(@NonNull Application application)
    {
        super(application);
        AppDatabase appDatabase = AppDatabase.getAppDatabaseInstance(application);
        liveDataFromRoom = appDatabase.getStudentDao().queryAll();
    }

    public MutableLiveData<String> getLiveData()
    {
        return liveData;
    }

    public LiveData<List<Student>> getLiveDataFromRoom()
    {
        return liveDataFromRoom;
    }
}
