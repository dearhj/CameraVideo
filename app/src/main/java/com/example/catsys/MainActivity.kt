package com.example.catsys

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var text: TextView? = null
    private var edit: EditText? = null
    var data: String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.TextView)
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.android.serial.BARCODEPORT_RECEIVEDDATA_ACTION")
        registerReceiver(receiver, intentFilter)
//        edit = findViewById(R.id.edit)
//        var data = ""
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
//        val value = sp.getString("key", "null")
//        if(value != "null") {
//            edit?.setText(value, TextView.BufferType.EDITABLE)
//            val result = readData(value)
//            text?.text = result
//        }
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
//        findViewById<Button>(R.id.button).setOnClickListener {
//            val result = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS)
////            if(edit?.text.toString() != "") {
////                sp.edit().putString("key", edit?.text.toString()).apply()
////                val result = readData(edit?.text.toString())
//                data = if (text?.text != "") {
//                    text?.text.toString() + "\n" + result
//                } else result.toString()
//                text?.text = data
////            }
//        }
//        findViewById<Button>(R.id.clear).setOnClickListener {
//            text?.text = ""
//        }
    }

//    private fun readData(value: String?): String {
//        return try {
//            if(value != null) {
//                val file = File(value)
//                if(file.exists()) {
//                    val reader = BufferedReader(FileReader(file))
//                    val result = reader.readLine()
//                    println("执行到了这里，值为： $result")
//                    reader.close()
//                    result
//                } else "节点不存在"
//            } else "节点值为空"
//        } catch (e: Exception) {
//            e.printStackTrace()
//            "fail"
//        }
//    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action != null && action == "com.android.serial.BARCODEPORT_RECEIVEDDATA_ACTION") {
                data = intent.getStringExtra("DATA")
                data = if (text?.text != "") {
                    text?.text.toString() + "\n" + data
                } else data.toString()
                text?.text = data
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}