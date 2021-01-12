package com.example.matrix_io_canary_demo

import android.util.Log
import com.tencent.mrs.plugin.IDynamicConfig
import kotlin.math.log

/**
 *  author : wanlinruo
 *  date : 2021/1/11 16:04
 *  contact : ln.wan@tuya.com
 *  description :
 */
class DynamicConfigImpl : IDynamicConfig {

    override fun get(key: String?, defStr: String?): String {
        Log.d("haha", "key:$key, defStr:$defStr")
        return defStr?:""
    }

    override fun get(key: String?, defInt: Int): Int {
        Log.d("haha", "key:$key, defInt:$defInt")
        return defInt
    }

    override fun get(key: String?, defLong: Long): Long {
        Log.d("haha", "key:$key, defLong:$defLong")
        return defLong
    }

    override fun get(key: String?, defBool: Boolean): Boolean {
        Log.d("haha", "key:$key, defBool:$defBool")
        return defBool
    }

    override fun get(key: String?, defFloat: Float): Float {
        Log.d("haha", "key:$key, defFloat:$defFloat")
        return defFloat
    }
}