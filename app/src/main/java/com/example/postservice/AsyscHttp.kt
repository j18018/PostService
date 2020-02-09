package com.example.postservice

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class AsyncHttp:AsyncTask<String, Int, Boolean> {
    var urlConnection: HttpURLConnection? = null
    var flg:Boolean = false

    var name:String? = null
    var value:Double? = null

    constructor(name:String,value:Double){
        this.name = name
        this.value = value
    }
    override fun doInBackground(vararg params: String?): Boolean {
        var urlinput:String = "http://192.168.11.10/upload/post.php"
        try{
            var url: URL = URL(urlinput)
            var urlConnection = url.openConnection() as HttpURLConnection
            urlConnection!!.setRequestMethod("POST")
            urlConnection!!.setDoOutput(true)

        //POST用パラメータ
        var postDataSample:String = "name=" + name + "&text=" + value
        //POSTパラメータ設定
        var out: OutputStream = urlConnection.getOutputStream()
        out.write(postDataSample.toByteArray())
        out.flush()
        out.close()
        Log.d("test",postDataSample)
        //レスポンスを受け取る
        urlConnection.getInputStream()

        flg = true
    }catch (e: MalformedURLException){
        e.printStackTrace()
    }catch (e: IOException){
        e.printStackTrace()
    }
    return flg
}

}