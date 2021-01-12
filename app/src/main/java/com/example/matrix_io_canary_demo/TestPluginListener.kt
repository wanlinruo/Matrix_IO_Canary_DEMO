package com.example.matrix_io_canary_demo

import android.content.Context
import android.util.Log
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.plugin.Plugin
import com.tencent.matrix.report.Issue

/**
 *  author : wanlinruo
 *  date : 2021/1/11 16:02
 *  contact : ln.wan@tuya.com
 *  description :
 */
class TestPluginListener(context: Context) : DefaultPluginListener(context) {

    override fun onInit(plugin: Plugin?) {
        super.onInit(plugin)
        Log.d("haha", "TestPluginListener-onInit")
    }

    override fun onStart(plugin: Plugin?) {
        super.onStart(plugin)
        Log.d("haha", "TestPluginListener-onStart")
    }

    override fun onStop(plugin: Plugin?) {
        super.onStop(plugin)
        Log.d("haha", "TestPluginListener-onStop")
    }

    override fun onDestroy(plugin: Plugin?) {
        super.onDestroy(plugin)
        Log.d("haha", "TestPluginListener-onDestroy")
    }

    /**
     * 检测结果的回调
     *
     *
     * eg：tag[io]type[2];key[null];content[{"path":"\/data\/user\/0\/com.example.matrix_io_canary_demo\/cache\/a_long.txt","size":40960000,"op":80000,"buffer":512,"cost":633,"opType":2,"opSize":40960000,"thread":"main","stack":"com.example.matrix_io_canary_demo.MainActivity.writeLongSth(MainActivity.kt:86)\ncom.example.matrix_io_canary_demo.MainActivity.access$writeLongSth(MainActivity.kt:18)\ncom.example.matrix_io_canary_demo.MainActivity$onCreate$1.onClick(MainActivity.kt:31)\nandroid.view.View.performClick(View.java:6637)\ncom.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992)\nandroid.view.View.performClickInternal(View.java:6614)\nandroid.view.View.access$3100(View.java:790)\nandroid.view.View$PerformClick.run(View.java:26160)\nandroid.app.ActivityThread.main(ActivityThread.java:7042)\njava.lang.reflect.Method.invoke(Native Method)\n","repeat":0,"tag":"io","type":2,"process":"com.example.matrix_io_canary_demo","time":1610443483898}]
     *
     * 其中字段的含义如下：
     * @see [https://github.com/Tencent/matrix/wiki/Matrix-Android--data-format]
     *
    IO 当前存在四种类型的上报,的具体信息如下：
        tag: io
        type，耗时这边的类型有两种 a. MAIN_THREAD_IO=1, 在主线程IO超过200ms
                                b. BUFFER_TOO_SMALL=2, 重复读取同一个文件,同一个堆栈超过3次
                                c. REPEAT_IO=3, 读写文件的buffer过小，即小于4k
                                d. CLOSE_LEAK=4, 文件泄漏
        path: 文件的路径
        size: 文件的大小
        cost: 读写的耗时
        stack: 读写的堆栈
        op: 读写的次数
        buffer: 读写所用的buffer大小，要求大于4k
        thread: 线程名
        opType: 1为读，2为写
        opSize: 读写的总大小
        repeat:
            a. REPEAT_IO : 重复的次数
            b. Main_IO：1 - 单次操作 2 - 连续读写 3 -2种行为
     *
     */
    override fun onReportIssue(issue: Issue?) {
        super.onReportIssue(issue)
        Log.d("haha", "TestPluginListener-onReportIssue:${issue.toString()}")
    }
}