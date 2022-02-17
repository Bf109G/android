package com.chen.app.view.ui.fragment


import com.alibaba.android.arouter.facade.annotation.Route
import com.chen.app.BR
import com.chen.app.R
import com.chen.app.base.BaseFragment

import com.chen.app.databinding.FragHomeBinding
import com.chen.app.router.RouterFragmentPath
import com.chen.app.viewmodel.HomeViewModel

/**
 * Author by chennan
 * Date on 2021/11/9
 * Description
 */
@Route(path = RouterFragmentPath.HOME)
class HomeFragment: BaseFragment<FragHomeBinding, HomeViewModel>() {
    override val mLayout: Int
        get() = R.layout.frag_home

    override fun initVariableId(): Int {
       return BR.viewModel
    }

    override fun initParams() {

    }

    override fun initData() {

    }

    override fun initViewObservable() {

    }

    override fun onReload() {

    }
}