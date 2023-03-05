package com.example.myapplication.model.room.dao;

/*
 * Dao ： 标注数据库操作的类。
 * Query ： 包含所有Sqlite语句操作。
 * Insert ： 标注数据库的插入操作。
 * Delete ： 标注数据库的删除操作。
 * Update ： 标注数据库的更新操作。
 */

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.room.bean.Person;

import java.util.List;

/**
 * 数据访问对象（DAO）
 * DAO 是一个接口/抽象类，编译程序时，由 Room 自动生成对应的实现类
 * 通过注解的方式来提供 CRUD 抽象方法
 */
@Dao
public interface PersonDao
{
    /*
     * OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务
     * OnConflictStrategy.ROLLBACK：冲突策略是回滚事务
     * OnConflictStrategy.ABORT：冲突策略是终止事务
     * OnConflictStrategy.FAIL：冲突策略是事务失败
     * OnConflictStrategy.IGNORE：冲突策略是忽略冲突
     */

    /**
     * 根据 id 插入一条或多条记录（插入重复时直接覆盖旧数据）
     *
     * @param persons 一个或多个 Person 实例
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person... persons);

    /**
     * 根据 id 删除一条或多条记录
     *
     * @param persons 一个或多个 Person 实例
     */
    @Delete
    void delete(Person... persons);

    /**
     * 根据 id 删除所有记录
     */
    @Query("delete from Person")
    void deleteAll();

    /**
     * 根据 id 更新一条或多条记录
     *
     * @param persons 一个或多个 Person 实例
     */
    @Update
    void update(Person... persons);

    /*
     * Dao 接口中，使用 @Query 注解定义查询方法时，返回值的类型可以是 Java 集合类中的任何一种，
     * 如 List<Person>、Set<Person> 等等，但是不支持直接使用 ArrayList<Person> 作为返回类型。
     *
     * 这是因为 Room 使用的是 Java 的通用数据访问层（Java Persistence API），该层仅支持返回 List 或 数组 类型的查询结果，而不支持其他类型。
     * 虽然 ArrayList 是 List 的子类，但是为了避免出现意外的错误，官方文档建议使用 List 作为返回类型。
     */

    /**
     * 查询所有记录
     *
     * @return 由 Person 实例构成的 List
     */
    @Query("select * from Person")
    List<Person> queryAll();

    /**
     * 根据 id 查询一条记录
     *
     * @param id Person 实例的 id 属性
     * @return 一个 Person 实例
     */
    @Query("select * from Person where P_id= :id")
    Person queryById(int id);

    /**
     * 根据 id 查询一条或多条记录
     *
     * @param ids 由 Person 实例的 id 属性构成的 List
     * @return 一个或多个 Person 实例
     */
    @Query("select * from Person where P_id in (:ids)")
    List<Person> queryAllByIds(List<Integer> ids);

    /**
     * 根据 name 和 age 查询一条记录
     *
     * @param name Person 实例的 name 属性
     * @param age  Person 实例的 age 属性
     * @return 一个 Person 实例
     */
    @Query("select * from Person where P_name = :name and P_age = :age")
    Person queryByNameAge(String name, int age);
}
