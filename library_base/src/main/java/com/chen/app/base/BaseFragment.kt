package com.chen.app.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * Author by chennan
 * Date on 2021/11/4
 * Description
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment(), IBaseView {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    private var mLasView: View? = null
    private var mViewModelId: Int = 0
    private var mIsNavigationViewInit = false

    @get:LayoutRes
    protected abstract val mLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(mLasView == null){
            mBinding = DataBindingUtil.inflate(inflater, mLayout, container,false)
            mBinding.lifecycleOwner = this
            mLasView = mBinding.root
        }
        return mLasView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(!mIsNavigationViewInit){
            super.onViewCreated(view, savedInstanceState)
            initViewDataBinding()
            initData()
            initViewObservable()
        }
        mIsNavigationViewInit = true
    }

    private fun initViewDataBinding(){
        mViewModelId = initVariableId()
        mViewModel = createViewModal(this)
        mBinding.setVariable(mViewModelId, mViewModel)
    }

    abstract fun initVariableId(): Int

    private fun <VM: ViewModel> createViewModal(fragment: Fragment): VM{
        val type = javaClass.genericSuperclass
        val modelClass: Class<VM> = if(type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<VM>
        }else {
            BaseViewModel::class.java as Class<VM>
        }
        return ViewModelProvider(fragment).get(modelClass)
    }

    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(context, clz))
    }

    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    open fun initToolbar() {}
}