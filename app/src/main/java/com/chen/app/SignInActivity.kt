package com.chen.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.chen.app.R
import com.chen.app.databinding.ActivitySignInBinding

/**
 * Author by chennan
 * Date on 2021/10/28
 * Description
 */
@Route(path = "/signIn/signInActivity")
class SignInActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
    }
}