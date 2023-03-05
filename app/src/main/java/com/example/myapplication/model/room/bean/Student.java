package com.example.myapplication.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Student",
        foreignKeys = @ForeignKey(
                entity = Person.class,
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE,
                parentColumns = "P_id", childColumns = "P_id"
        ),
        indices = {
                @Index(value = "S_id"),
                @Index(value = "P_id"),
                @Index(value = {"S_id", "P_id"})
        }
)
public class Student
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "S_id")
    private int studentId;

    @ColumnInfo(name = "P_id")
    private int personId;

    public Student(int studentId, int personId)
    {
        this.studentId = studentId;
        this.personId = personId;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public int getPersonId()
    {
        return personId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public void setPersonId(int personId)
    {
        this.personId = personId;
    }
}
