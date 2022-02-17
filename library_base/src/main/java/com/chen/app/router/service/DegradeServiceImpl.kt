package com.chen.app.router.service

import android.content.Context
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.chen.app.utils.KLog

/**
 * Author by chennan
 * Date on 2022/2/17
 * Description
 */
@Route(path = "/service/degradeService")
class DegradeServiceImpl: DegradeService {
    override fun init(context: Context?) {
        KLog.d("DegradeServiceImpl", "DegradeServiceImpl初始化了")
    }

    override fun onLost(context: Context?, postcard: Postcard?) {
        KLog.d("DegradeServiceImpl", "路由不存在")
    }
}