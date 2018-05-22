package com.saveexample.saveexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.*
import android.content.res.AssetFileDescriptor
import java.io.IOException
import android.media.*
import android.support.v4.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    var etUser:EditText? = null
    var etPass:EditText? = null
    var btnLogin:Button? = null
    var tv1:TextView? = null

    private var myPreferences = "PROFILE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharePre = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

        tv1 = findViewById(R.id.tv1)
        val user = sharePre.getString("USER" , "NULL")
        val pass = sharePre.getString("PASS" , "NULL")
        tv1?.text = "Username : "+user+"\nPassword : "+pass

        val fontVlump = ResourcesCompat.getFont(this,R.font.sanamdeklen)
        tv1?.typeface = fontVlump

        etUser = findViewById(R.id.etUser)
        etPass = findViewById(R.id.etPass)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin?.setOnClickListener {
            val username = etUser?.text.toString()
            val password = etPass?.text.toString()
            tv1?.text = "USER : " + username + "\nPass : " + password

            val editor = sharePre.edit()
            editor.putString("USER", username)
            editor.putString("PASSWORD", password)
            editor.apply()

            Toast.makeText(this, "Save Complete", Toast.LENGTH_SHORT).show()
            this.openSound()
        }
    }

    fun openSound()
        {
            val mp = MediaPlayer()
            if (mp.isPlaying){
                mp.stop()
            }
            try{
                mp.reset()
                var afd:AssetFileDescriptor? = null
                afd = assets.openFd("bell.mp3")
                mp.setDataSource(afd.fileDescriptor,afd.startOffset,afd.length)
                mp.prepare()
                mp.start()
            }catch (e: IllegalStateException){
                e.printStackTrace()
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
