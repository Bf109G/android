package com.chen.app.oom

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.chen.app.R
import com.chen.app.base.BaseActivity
import com.chen.app.base.BaseViewModel
import com.chen.app.databinding.ActivityOomBinding
import com.chen.app.router.RouterActivityPath
import java.lang.ref.WeakReference

/**
 * Author by chennan
 * Date on 2022/3/2
 * Description
 */
@Route(path = RouterActivityPath.OOM)
class OOMActivity : BaseActivity<ActivityOomBinding, BaseViewModel>() {

    private var mHandler = Handler(Looper.getMainLooper())

    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

        }
    }

   companion object{
       var textView: TextView? = null
   }

    override val mLayout: Int
        get() = R.layout.activity_oom

    override fun initVariableId(): Int {
        return 0
    }

    override fun initParams() {
        // 静态变量造成泄露，避免在Activity中使用static修饰变量
        textView = TextView(this)
        // 单利持有Context, 换成application
        LoginManager.getInstance(this)
        // 非静态内部类(Handler,Thread,AsyncTask), 可采用静态内部类+弱引用方式，或者在Activity销毁时置空
        mHandler.postDelayed({
            ContextCompat.getColor(this, R.color.purple_200)
        }, 6000)
        // 未取消注册或回调导致内存泄(广播), 在Activity销毁时注销
        registerReceiver(mReceiver, IntentFilter())
        // Timer和TimerTask泄露,  在Activity销毁时cancel
        // 资源未释放或者关闭(IO, File, Sqlite, Cursor)，即时关闭
        // 使用bitmap对象时，不要在主线程处理图片，即时recycle()，并置null
        // WebView造成泄露
        // 先从父控件中移除WebView
        //    mWebViewContainer.removeView(mWebView);
        //    mWebView.stopLoading();
        //    mWebView.getSettings().setJavaScriptEnabled(false);
        //    mWebView.clearHistory();
        //    mWebView.removeAllViews();
        //    mWebView.destroy();
    }

    override fun initData() {
        finish()
    }

    override fun initViewObservable() {

    }

    override fun onReload() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null)
        unregisterReceiver(mReceiver)
    }

    class MyHandler(activity: Activity) : Handler(Looper.getMainLooper()) {
        private var activityWeakReference: WeakReference<Activity> =
            WeakReference<Activity>(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = activityWeakReference.get()
            if (activity != null) {

            }
        }
    }
}