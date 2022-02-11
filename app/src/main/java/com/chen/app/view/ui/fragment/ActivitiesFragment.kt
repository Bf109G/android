package com.chen.app.view.ui.fragment

import com.chen.app.R
import com.chen.app.base.BaseFragment
import com.chen.app.base.BaseViewModel
import com.chen.app.databinding.FragActivitiesBinding
import com.chen.app.databinding.FragHomeBinding

/**
 * Author by chennan
 * Date on 2021/11/9
 * Description
 */
class ActivitiesFragment: BaseFragment<FragActivitiesBinding, BaseViewModel>() {
    override val mLayout: Int
        get() = R.layout.frag_activities

    override fun initVariableId(): Int {
       return 0
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