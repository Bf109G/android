package com.chen.app.base

/**
 * Author by chennan
 * Date on 2021/11/1
 * Description
 */
interface IBaseView {

    fun initParams()

    fun initData()

    fun initViewObservable()

    fun onReload()
}