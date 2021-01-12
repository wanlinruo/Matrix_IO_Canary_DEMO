package com.example.matrix_io_canary_demo

import android.app.Application
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig

/**
 *  author : wanlinruo
 *  date : 2021/1/11 15:57
 *  contact : ln.wan@tuya.com
 *  description :
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // build matrix
        val builder: Matrix.Builder = Matrix.Builder(this)

        // add general pluginListener
        builder.patchListener(TestPluginListener(this))

        // dynamic config
        val dynamicConfig = DynamicConfigImpl()

        // init plugin
        val ioCanaryPlugin = IOCanaryPlugin(
            IOConfig.Builder()
                .dynamicConfig(dynamicConfig)
                .build()
        )

        // add to matrix
        builder.plugin(ioCanaryPlugin)

        // init matrix
        Matrix.init(builder.build());

        // start plugin
        ioCanaryPlugin.start()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}