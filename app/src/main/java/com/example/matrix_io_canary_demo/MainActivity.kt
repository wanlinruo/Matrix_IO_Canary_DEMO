package com.example.matrix_io_canary_demo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.plugin.Plugin
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val EXTERNAL_STORAGE_REQ_CODE = 0x1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPer()

        btn_file_io.setOnClickListener {
            writeLongSth()
        }

        btn_small_buffer.setOnClickListener {
            readSth()
        }
    }

    private fun requestPer() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(this, "please give me the permission", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    EXTERNAL_STORAGE_REQ_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            EXTERNAL_STORAGE_REQ_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    private fun writeLongSth() {
        try {
            val fileName = cacheDir.path + File.separator + "a_long.txt"
            val f = File(fileName)
            if (!f.exists()) {
                f.createNewFile()
            } else {
                f.delete()
            }

            val data = ByteArray(512)
            for (i in data.indices) {
                data[i] = 'a'.toByte()
            }
            val fos = FileOutputStream(f)
            for (i in 0 until 10000 * 8) {
                fos.write(data)
            }
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readSth() {
        try {

            val fileName = cacheDir.path + File.separator + "a_long.txt"
            val f = File(fileName)
            if (!f.exists()) {
                f.createNewFile()
            }

            val buf = ByteArray(400)
            val fis = FileInputStream(f)
            var count = 0
            while (fis.read(buf) != -1) {
                Log.d("haha", "read ${++count}")
//                MatrixLog.i(TAG, "read %d", ++count);
            }
            fis.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val fileName = cacheDir.path + File.separator + "a_long.txt"
        val f = File(fileName)
        if (f.exists()) {
            f.delete()
        }
    }
}