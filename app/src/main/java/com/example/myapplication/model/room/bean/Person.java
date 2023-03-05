package com.example.myapplication.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/*
 * Entity ： 数据表的实体类。
 * PrimaryKey ： 每一个实体类都需要一个唯一的标识。
 * ColumnInfo ： 数据表中字段名称。
 * Ignore ： 标注不需要添加到数据表中的属性。
 * Embedded ： 实体类中引用其他实体类。
 * ForeignKey ： 外键约束。
 *
 * 单列索引          @Entity(indices = {&#064;Index(value  = "name")} )
 * 单列索引唯一性    @Entity(indices = {&#064;Index(value  = "name", unique = true)} )
 * 组合索引          @Entity(indices ={&#064;Index(value  = {"name","age"})} )
 * 组合索引唯一性    @Entity(indices ={&#064;Index(value  = {"name","age"},unique = true)} )
 */

/**
 * Person 实体类
 * 通过 public 修饰符或 getter、setter 来确保 Room 数据库能正常访问每个字段
 */
@Entity(
        tableName = "Person",
        indices = {
                @Index(value = "P_name"),
                @Index(value = {"P_name", "P_age"})
        }
)
// SQLite 中的表名和列名不区分大小写
public class Person
{
    @PrimaryKey(autoGenerate = true)  // 设置主键自增长
    @ColumnInfo(name = "P_id")  // Room 默认使用实体类属性名作为数据库字段名
    private int id;
    @ColumnInfo(name = "P_name")
    private String name;
    @ColumnInfo(name = "P_age")
    private int age;

    //@Ignore  // 设置需要忽略的属性，即该属性不作为字段存入数据库

    public Person(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
