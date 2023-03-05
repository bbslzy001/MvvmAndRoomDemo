package com.example.myapplication.model.entity;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import java.util.List;

public class MyTable
{
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();  // 与 ObservableField<Integer> 相同，但 ObservableInt 更加高效
    public final ObservableField<List<String>> info = new ObservableField<>();

    public MyTable(String name, int age, List<String> info)
    {
        this.name.set(name);
        this.age.set(age);
        this.info.set(info);
    }
}
