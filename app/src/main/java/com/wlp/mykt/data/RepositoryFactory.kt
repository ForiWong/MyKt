package com.wlp.mykt.data

import com.wlp.mykt.data.remote.ApiService
import com.wlp.mykt.data.repository.ExampleRepositoryImpl
import com.wlp.mykt.data.repository.ExampleRepository

/**
object的使用

其中一种用法：对象声明（object declaration）
将类的声明和定义该类的单例对象结合在一起（即通过object就实现了单例模式）
对象声明中不能包含构造器（包括主构造器和次级构造器）
对象声明实例解析以及在kotlin和java代码中的调用

object UserManager {
fun saveUser()
}

// 反编译出的Java代码
public final class UserManager {
public static final UserManager INSTANCE;

public final void saveUser() {
}

private UserManager() {
}

static {
UserManager var0 = new UserManager();
INSTANCE = var0;
}
}
在kotlin和java代码中，它们的调用方式有点差别：

kotlin代码调用：UserManager.saveUser()
java代码调用：UserManager.INSTANCE.saveUser();
 */

/**
 * 提供获取各种仓库的单例类
 * 这里使用object实现单例模式
 */
object RepositoryFactory {

    /**
     * 这里获取到仓库供ViewModel使用 private val ExampleRepository: Repository
     * 这里的参数都是单例类
     * ApiService 网络
     * DbManager 数据库
     */
    fun getExampleRepository(api: ApiService/*, db: DbManager*/): ExampleRepository =
        ExampleRepositoryImpl(api)

}