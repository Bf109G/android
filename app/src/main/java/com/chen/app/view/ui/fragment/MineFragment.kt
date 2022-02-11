package com.chen.app.view.ui.fragment

import com.chen.app.R
import com.chen.app.base.BaseFragment
import com.chen.app.base.BaseViewModel
import com.chen.app.databinding.FragHomeBinding
import com.chen.app.databinding.FragMineBinding

/**
 * Author by chennan
 * Date on 2021/11/9
 * Description
 */
class MineFragment: BaseFragment<FragMineBinding, BaseViewModel>() {
    override val mLayout: Int
        get() = R.layout.frag_mine

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