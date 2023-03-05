package com.example.myapplication.utils;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

import com.example.myapplication.model.room.bean.Student;

import java.util.List;

public class TextViewBindingAdapter
{
    @BindingAdapter("app:students")
    public static void setStudents(TextView textView, LiveData<List<Student>> students)
    {
        /* 在使用 LiveData 时，我们必须始终考虑数据是否为空，因为 LiveData 可能在观察期间为空 */
        if (students != null && students.getValue() != null)
        {
            StringBuilder sb = new StringBuilder();
            for (Student student : students.getValue())
            {
                sb.append("personId: ").append(student.getPersonId()).append("  studentId: ").append(student.getStudentId()).append("\n");
            }
            textView.setText(sb.toString());
        }
    }
}
