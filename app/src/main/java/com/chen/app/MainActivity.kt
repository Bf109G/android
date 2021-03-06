package com.chen.app

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.chen.app.base.BaseActivity
import com.chen.app.databinding.ActivityMainBinding
import com.chen.app.router.RouterFragmentPath
import com.chen.app.view.ui.fragment.HomeFragment
import com.chen.app.view.ui.fragment.MineFragment
import com.chen.app.viewmodel.MainViewModel
import com.chen.app.widget.bottomnavigation.BottomNavigationBar
import com.chen.app.widget.bottomnavigation.BottomNavigationItem


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var mFrags: MutableList<Fragment>? = null

    override val mLayout: Int
        get() = R.layout.activity_main

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParams() {

    }

    override fun initData() {
        initFragment()
        initBottomNavigation()
    }

    override fun initViewObservable() {
        mViewModel.requestCameraPermissions.observe(this, Observer {

        })

        mViewModel.getText().observe(this, Observer {
//            mBinding.textView.text = it
        })
    }

    override fun onReload() {}

    private fun initBottomNavigation() {
        mBinding.bottomNavigation
            .setBarBackgroundColor(R.color.white)
            .setMode(BottomNavigationBar.MODE_FIXED_ORIGIN_ICON)
            .setInActiveColor(R.color.color_CCCCCC)
            .setActiveColor(R.color.color_353535)
            .addItem(BottomNavigationItem(R.drawable.ic_s_nav_home, "้ฆ้กต"))
            .addItem(BottomNavigationItem(R.drawable.ic_s_nav_me, "ๆ็"))
            .initialise()

        mBinding.bottomNavigation.setTabSelectedListener(object :
            BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                commitAllowingStateLoss(position)
            }

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {}

        })
    }

    private fun initFragment() {
        val homeFrag = ARouter.getInstance().build(RouterFragmentPath.HOME).navigation() as HomeFragment
        val mineFrag = ARouter.getInstance().build(RouterFragmentPath.MINE).navigation() as MineFragment
        mFrags = ArrayList()
        mFrags?.add(homeFrag)
        mFrags?.add(mineFrag)
        commitAllowingStateLoss(0)
    }

    private fun commitAllowingStateLoss(navIndex: Int) {
        mFrags?.let {
            val transaction = supportFragmentManager.beginTransaction()
            hideFrag()
            var currentFragment = supportFragmentManager.findFragmentByTag(navIndex.toString() + "")
            if (currentFragment != null) {
                transaction.show(currentFragment)
            } else {
                currentFragment = it[navIndex]
                transaction.add(R.id.mainContainer, currentFragment, navIndex.toString() + "")
            }
            transaction.commitAllowingStateLoss()
        }
    }

    private fun hideFrag() {
        mFrags?.let {
            val transaction = supportFragmentManager.beginTransaction()
            for (i in 0 until it.size) {
                val currentFragment = supportFragmentManager.findFragmentByTag(i.toString() + "")
                if (currentFragment != null) {
                    transaction.hide(currentFragment)
                }
            }
            transaction.commitAllowingStateLoss()
        }
    }
}