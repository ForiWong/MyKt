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
? 多个VM childViewModel的用法
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
