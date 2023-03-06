package com.example.myapplication.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.room.bean.Student;
import com.example.myapplication.model.room.database.AppDatabase;

import java.util.List;

/**
 * 采用可以接受 Application 参数的 AndroidViewModel 作为父类
 * 采用不接受参数的 ViewModel 作为父类，需要使用全局 Context
 */
public class LiveDataViewModel extends ViewModel
{
    private final MutableLiveData<String> liveData = new MutableLiveData<>();

    private final LiveData<List<Student>> liveDataFromRoom;

    public LiveDataViewModel()
    {
        AppDatabase appDatabase = AppDatabase.getAppDatabaseInstance();
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
