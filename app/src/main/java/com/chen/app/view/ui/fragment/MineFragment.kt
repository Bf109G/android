package com.chen.app.view.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.chen.app.R
import com.chen.app.base.BaseFragment
import com.chen.app.base.BaseViewModel
import com.chen.app.databinding.FragHomeBinding
import com.chen.app.databinding.FragMineBinding
import com.chen.app.router.RouterFragmentPath

/**
 * Author by chennan
 * Date on 2021/11/9
 * Description
 */
@Route(path = RouterFragmentPath.MINE)
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