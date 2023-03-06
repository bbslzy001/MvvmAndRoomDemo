package com.example.myapplication.model.repository;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model.room.bean.Person;
import com.example.myapplication.model.room.dao.PersonDao;
import com.example.myapplication.model.room.database.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过 Repository 与 Activity 等业务层进行交互，而不是直接操作 Dao 层
 * （确保 activity 不需要了解数据库的细节和实现细节，而是将数据库访问逻辑委托给 Repository 来处理）
 * <p>
 * 实现业务逻辑并管理数据库操作
 *
 * Room 数据库不允许在主线程中进行 CRUD 操作（数据库操作耗时较长），以避免主线程阻塞，提高运行效率
 *
 * 查询操作需要实时更新 UI：
 * 查询操作通常需要在执行完成后通知 UI 进行更新
 * 可以使用 LiveData 类，自动创建后台线程并执行查询操作，执行结束后，自动将结果返回给 UI
 *
 * 增删改操作不需要实时更新 UI：
 * 增删改操作通常不需要在执行完成后通知 UI 进行更新
 * 可以使用 Executors 类，手动创建后台线程并执行增删改操作，执行结束后，自动关闭线程
 */
public class PersonRepository
{
    private final PersonDao personDao;
    private final ExecutorService executor;

    public PersonRepository()
    {
        AppDatabase appDatabase = AppDatabase.getAppDatabaseInstance();
        personDao = appDatabase.getPersonDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Person... persons)
    {
        executor.execute(() -> personDao.insert(persons));
    }

    public void delete(Person... persons)
    {
        executor.execute(() -> personDao.delete(persons));
    }

    public void update(Person... persons)
    {
        executor.execute(() -> personDao.update(persons));
    }

    public LiveData<List<Person>> queryAll()
    {
        return personDao.queryAll();
    }

    public LiveData<Person> queryById(int id)
    {
        return personDao.queryById(id);
    }

    public LiveData<List<Person>> queryByIds(int... ids)
    {
        return personDao.queryByIds(ids);
    }

    public LiveData<Person> queryByNameAge(String name, int age)
    {
        return personDao.queryByNameAge(name, age);
    }
}
