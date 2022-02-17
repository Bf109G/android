package com.chen.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


@Route(path = "/detail/activity2")
class MainActivity2 : AppCompatActivity() {


    @JvmField
    @Autowired
    var flag: Boolean = false

    @JvmField
    @Autowired
    var total: Int = 0

    @Autowired
    @JvmField
    var name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}