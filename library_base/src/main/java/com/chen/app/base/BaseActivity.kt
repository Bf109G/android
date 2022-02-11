package com.chen.app.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * Author by chennan
 * Date on 2021/10/29
 * Description
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseView {

    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    private var mViewModelId: Int = 0

    @get:LayoutRes
    protected abstract val mLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
        initViewDataBinding(savedInstanceState)
        initData()
        initViewObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        if (mLayout != 0) {
            mBinding = DataBindingUtil.setContentView(this, mLayout)
            mViewModelId = initVariableId()
            mViewModel = createViewModel(this)
            mBinding.lifecycleOwner = this
            //关联ViewModel
            mBinding.setVariable(mViewModelId, mViewModel)
            //让ViewModel拥有View的生命周期感应
            lifecycle.addObserver(mViewModel)
        }
    }

    abstract fun initVariableId(): Int

    private fun <VM : ViewModel> createViewModel(activity: AppCompatActivity): VM {
        val type = javaClass.genericSuperclass
        val modelClass: Class<VM> = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<VM>
        } else {
            BaseViewModel::class.java as Class<VM>
        }
        return ViewModelProvider(activity).get(modelClass)
    }

    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }

    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    open fun initToolbar() {}
}