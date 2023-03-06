package com.example.myapplication.ui.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.entity.MyTable;
import com.example.myapplication.model.repository.PersonRepository;
import com.example.myapplication.model.repository.StudentRepository;
import com.example.myapplication.model.room.bean.Person;
import com.example.myapplication.model.room.bean.Student;
import com.example.myapplication.ui.viewmodel.LiveDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private final MutableLiveData<String> onlyLiveData = new MutableLiveData<>();
    private final ObservableField<String> onlyDataBinding = new ObservableField<>();
    private LiveDataViewModel liveDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);  // 通过DataBinding库将Activity设置为给定的XML布局，并返回关联的绑定

        bindingLifecycleOwnerAndViewModel();

        DataBindingExample();
        LiveDataExample();
        LiveDataAndViewModelExample();
        RoomDatabaseExample();
    }

    /**
     * 一个有关 双向绑定 和 单向绑定 的示例
     */
    private void DataBindingExample()
    {
        /* 双向绑定 */
        binding.setEditor("测试内容");// 可以同时修改输入框内容和 editor 变量的值
        // binding.txt.setEditor("测试内容");  // 也可以同时修改输入框内容和 editor 变量的值，但该方式不符合双向数据绑定的思想，不建议使用

        /* 单向绑定 */
        MyTable myTable = new MyTable("李明", 28, List.of("1", "2", "3"));
        binding.setMyTable(myTable);

        /* 通过 binding 中的组件 Id 调用组件 */
        binding.btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                myTable.name.set("Ming Li");
                myTable.info.set(List.of("one", "two", "three"));
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                myTable.name.set("李明");
                myTable.info.set(List.of("1", "2", "3"));
            }
        });
    }

    /**
     * 一个有关 LiveData 的示例
     */
    private void LiveDataExample()
    {
        /* 单独使用 LiveData（自身可以感知生命周期，需要设置监听） */
        binding.setOnlyLiveData(onlyLiveData.getValue());  // 设置初始值
        onlyLiveData.observe(this, new Observer<String>()  // 设置数据监听，自身可以感知生命周期，只有在owner为onStart或onResume时才处于活跃状态
        {
            @Override
            public void onChanged(String s)
            {
                binding.setOnlyLiveData(s);  // 手动回调
                Log.d("test", "检测到 onlyLiveData 值改变，手动回调 setOnlyLiveData()");
            }
        });

        /* 单独使用 DataBinding（自身无法感知生命周期，不需要设置监听） */
        binding.setOnlyDataBinding(onlyDataBinding);  // 设置初始值
        binding.txtOnlyDataBinding.addTextChangedListener(new TextWatcher()  // 为了观察DataBinding何时更新UI，给TextView设置监听
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                Log.d("test", "检测到 onlyDataBinding 值改变，自动回调 setOnlyDataBinding()");
            }
        });
    }

    /**
     * 一个使用 LiveData 和 ViewModel 的示例
     */
    private void LiveDataAndViewModelExample()
    {
        binding.txtLiveDataViewModel.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                Log.d("test", "检测到 liveDataViewModel 值改变，自动回调 setLiveDataViewModel()");
            }
        });
    }

    private void RoomDatabaseExample()
    {
        PersonRepository personRepository = new PersonRepository();
        StudentRepository studentRepository = new StudentRepository();
        personRepository.insert(new Person(0, "Room", 18));
        personRepository.insert(new Person(0, "Room", 18));
        studentRepository.insert(new Student(0, 2));
        studentRepository.insert(new Student(0, 2));

        binding.btnRoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                personRepository.delete(new Person(1, "Room", 18));
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        changeValue();
        changeValue2();
    }

    /**
     * 改变值，以查看UI更新状况
     */
    private void changeValue()
    {
        /* 改变 onlyLiveData 的值 */
        /* onStop() 方法被调用时是在主线程中执行的，使用 setValue() 方法更合适 */
        onlyLiveData.setValue("onlyLiveData 值改变");  // 在主线程同步设置 LiveData 的值，如果该方法被调用的线程不是主线程，会引发 RuntimeException 异常
        // onlyLiveData.postValue("onlyLiveData 值改变");  // 在后台线程异步设置 LiveData 的值，如果该方法被调用的线程是主线程，LiveData 变化的回调会被延迟到下一次主线程的消息循环队列中执行，以确保 UI 操作的安全性

        /* 改变 onlyDataBinding 的值 */
        onlyDataBinding.set("onlyDataBinding 值改变");
    }

    private void changeValue2()
    {
        liveDataViewModel.getLiveData().setValue("liveDataViewModel 值改变");
    }

    private void bindingLifecycleOwnerAndViewModel()
    {
        binding.setLifecycleOwner(this);  // 将 LifecycleOwner 对象(通常为Activity或Fragment的实例)传递给 binding

        liveDataViewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);  // 获取 ViewModel 实例

        binding.setLiveDataViewModel(liveDataViewModel);
    }
}