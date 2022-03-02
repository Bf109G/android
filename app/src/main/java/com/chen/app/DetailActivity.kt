package com.chen.app

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.chen.app.base.BaseActivity
import com.chen.app.base.BaseViewModel
import com.chen.app.databinding.ActivityDetailBinding
import com.chen.app.model.bean.UserInfo
import com.chen.app.router.RouterActivityPath
import com.chen.app.utils.KLog

/**
 * Author by chennan
 * Date on 2022/2/15
 * Description
 */
@Route(path = RouterActivityPath.DETAIL)
class DetailActivity : BaseActivity<ActivityDetailBinding, BaseViewModel>() {
    @JvmField
    @Autowired(name = "flag")
    var flag: Boolean = false

    @Autowired
    @JvmField
    var total: Int = 0

    @Autowired
    @JvmField
    var name: String? = null

    @Autowired
    @JvmField
    var userInfo: UserInfo? = null

    override val mLayout: Int
        get() = R.layout.activity_detail

    override fun initVariableId(): Int {
        return 0
    }

    override fun initParams() {
        KLog.i("initParams", "flag=${flag}")
        KLog.i("initParams", "total=${total}")
        KLog.i("initParams", "name=${name}")
        KLog.i("initParams", "userInfo=${userInfo}")
    }

    override fun initData() {

    }

    override fun initViewObservable() {

    }

    override fun onReload() {

    }
}

