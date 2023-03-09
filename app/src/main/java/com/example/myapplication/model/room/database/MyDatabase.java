package com.example.myapplication.model.room.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.application.MyApplication;
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
public abstract class MyDatabase extends RoomDatabase
{
    private static final String DB_NAME = "App.db";  // 数据库名称
    private static MyDatabase myDatabase;  // 持有数据库实例

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
     * 如果不使用全局 Context，该方法需要添加 Context 参数
     *
     * @return 数据库实例
     */
    public static MyDatabase getMyDatabaseInstance()
    {
        if (myDatabase == null) myDatabase = createMyDatabase();
        return myDatabase;
    }

    /**
     * 创建数据库
     *
     * 如果不使用全局 Context，该方法需要添加 Context 参数
     *
     * @return 数据库实例
     */
    private static MyDatabase createMyDatabase()
    {
        return Room.databaseBuilder(MyApplication.getContext(), MyDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();  // 允许主线程进行数据库操作（尽量避免）；构建实例时销毁原数据库（仅开发时使用）
        // return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }
}
