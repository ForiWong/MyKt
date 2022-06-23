package com.wlp.mykt.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wlp.mykt.base.viewmodel.BaseViewModel
import com.wlp.mykt.util.LogUtils

/**
 * Activity基类
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseView {
    lateinit var binding: V
    lateinit var viewModel: VM
    private var viewModelId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("onCreate(): ${this.javaClass.simpleName}")
        initData(savedInstanceState)

        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        //私有的ViewModel与View的契约事件回调逻辑
        registorUIChangeLiveDataCallBack()

        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("onDestroy():${this.javaClass.simpleName}")

        //解除ViewModel生命周期感应
        viewModel.let { lifecycle.removeObserver(it) }
        binding!!.unbind()
    }

    override fun initData(savedInstanceState: Bundle?) {
        //todo 传值给viewModel
    }

    override fun initViewObservable() {

    }

    /**
     * 注入绑定binding、获取viewModel
     */
    private fun initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, initContentView())
        viewModelId = initVariableId()
        viewModel = createViewModel()

        //关联ViewModel
        binding.setVariable(viewModelId, viewModel)
        //让ViewModel拥有View的生命周期感应
        viewModel?.let { lifecycle.addObserver(it) }
    }

    /**
     * 刷新布局
     */
    protected fun refreshLayout() {
        binding.setVariable(viewModelId, viewModel)
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 获取ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    abstract fun createViewModel(): VM

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    protected fun registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel.uiChangeLiveData.showDialogEvent.observe(this) { title -> showDialog(title) }
        //加载对话框消失
        viewModel.uiChangeLiveData.dismissDialogEvent.observe(this) { dismissDialog() }
        //提示确认框显示
        viewModel.uiChangeLiveData.remindDialogEvent.observe(this) { title -> remindDialog(title) }
        //提示确认框消失
        viewModel.uiChangeLiveData.dismissRemindDialogEvent.observe(this) { dismissRemindDialog() }
        //跳入新页面
        viewModel.uiChangeLiveData.startActivityEvent.observe(this) { params ->
            val clz = params!![BaseViewModel.ParameterField.CLASS] as Class<*>?
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            startActivity(clz, bundle)
        }
        //关闭界面
        viewModel.uiChangeLiveData.finishEvent.observe(this) { finish() }
        //关闭上一层
        viewModel.uiChangeLiveData.onBackPressedEvent.observe(this) { onBackPressed() }
    }

    fun showDialog(tip: String?) {
//        if (dialogBuilder == null) dialogBuilder = MaterialDialog.Builder(this).progress(true, 0)
//        dialog = dialogBuilder!!.title(tip!!).canceledOnTouchOutside(true).show()
    }

    fun dismissDialog() {
//        if (dialog != null && dialog!!.isShowing) {
//            dialog!!.dismiss()
//        }
    }

    fun remindDialog(title: String?) {

    }

    fun dismissRemindDialog() {

    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}