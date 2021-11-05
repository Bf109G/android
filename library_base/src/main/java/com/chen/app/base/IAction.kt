package com.chen.app.base

/**
 * Author by chennan
 * Date on 2021/11/1
 * Description
 */
interface IAction {
    fun alert(msg: String)

    fun showToast(text: String)

    fun showLoading(resId: Int)

    fun hideLoading()

    fun finish()

}