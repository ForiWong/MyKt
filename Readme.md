data
    entity 实体类
    local 本地数据，sp 或缓存数据
    db 数据库，GreenDao 或 Room
    remote 网络数据
    repository 统一仓库



------------------------------------------------------------------------
V binding
？view
√ 或layout

√ 创建viewmodel
√ BaseViewModel
√ 一个VM
? 多个VM  childViewModel的用法
Activity内部多个viewmodel? 实际上应该是可以多个的？
但是如果可以多个的话？viewmodel之间如何同步数据呢？

横竖屏切换数据存储

recyclerview databinding

model
  sp mkkv datastore
  network
  database

自定义结果集封装类ResultState

使用依赖注入的库dagger、Hilt

ViewModel+LiveData+Repository实现MVVM的设计模式：
Google于I/O大会上提出了Android Architecture Components，里面提到的架构化组件可以更好的帮助我们去做一些框架的工作。  
而ViewModel的概念和 LiveData的可被观察性则让我看到了在Activity/Fragment/View（而不是xml文件）中实现双向绑定的可能性。

repo中使用LiveData的弊端：
a.不支持线程切换
b.重度依赖 Lifecycle
Repo 中常见的数据请求有两类
    单发请求 suspend
    流式请求 Flow

App Startup组件
startup: InitializationProvider androidx.startup.Initializer

mapper 数据转换

util
//点击按钮重复点击

