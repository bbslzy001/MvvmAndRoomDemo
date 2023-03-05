package com.example.myapplication.model.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.room.bean.Person;
import com.example.myapplication.model.room.bean.Student;
import com.example.myapplication.model.room.dao.PersonDao;
import com.example.myapplication.model.room.dao.StudentDao;

/**
 * 数据库类
 * 提供与该数据库关联的 DAO 实例，应用程序通过 DAO 实例 访问数据库数据
 */
@Database(
        entities = {Person.class, Student.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase
{
    private static final String DB_NAME = "App.db";  // 数据库名称
    private static AppDatabase appDatabase;  // 持有数据库实例

    /**
     * 对于与数据库关联的 DAO 类，需要提供一个无参抽象方法用于返回 DAO 类的实例
     *
     * @return DAO类的实例
     */
    public abstract PersonDao getPersonDao();

    public abstract StudentDao getStudentDao();

    /**
     * 获取数据库实例（单例模式）
     *
     * @param context 上下文
     * @return 数据库实例
     */
    public static AppDatabase getAppDatabaseInstance(Context context)
    {
        if (appDatabase == null) appDatabase = createAppDatabase(context);
        return appDatabase;
    }

    /**
     * 创建数据库
     *
     * @param context 上下文
     * @return 数据库实例
     */
    private static AppDatabase createAppDatabase(Context context)
    {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).allowMainThreadQueries().build();  // 允许主线程进行数据库操作（尽量避免）
        // return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }
}
