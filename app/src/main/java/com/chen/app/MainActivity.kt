package com.chen.app

import com.chen.app.base.BaseActivity
import com.chen.app.base.BaseViewModel
import com.chen.app.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>(){
    override val mLayout: Int
        get() = R.layout.activity_main

    override fun initVariableId(): Int {
//       return BR.viewModel
        return 0
    }

    override fun initParams() {}

    override fun initData() {}

    override fun initViewObservable() {}

    override fun onReload() {}
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.textView.text = "123"
//    }
}