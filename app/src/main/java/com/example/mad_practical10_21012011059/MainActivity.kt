package com.example.mad_practical10_21012011059

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab:FloatingActionButton=findViewById(R.id.fab)
        fab.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val data = HttpRequest().makeServiceCall(
                        "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                        "dchj8v1b6qqdjzbqood1jgpachyfzlw58r540gru")
                    withContext(Dispatchers.Main) {
                        try {
                            if(data != null)
                                runOnUiThread{getPersonDetailsFromJson(data)}
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getPersonDetailsFromJson(sJson: String) {
        val personList = ArrayList<Person>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Person(jsonObject)
                personList.add(person)
            }
            val personlistview=findViewById<ListView>(R.id.listview1)
            personlistview.adapter = PersonAdepter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }

   /* fun setArraytoListView(){
        val personlistview=findViewById<ListView>(R.id.listview1)
        val array= arrayListOf<Person>(
            Person("vfgfg", "tyrdytrf",  "abc@gmail.com",  "938387635",  "sdftsfat", 90.2,  239.3),
            Person("vfgfg", "tyrdytrf",  "abc@gmail.com",  "938387635",  "sdftsfat", 873.2,  233.3),
            Person("vfgfg", "tyrdytrf",  "abc@gmail.com",  "938387635",  "sdftsfat", 327.3,  123.4),
            Person("vfgfg", "tyrdytrf",  "abc@gmail.com",  "938387635",  "sdftsfat", 44.4,  23.42),
            Person("vfgfg", "tyrdytrf",  "abc@gmail.com",  "938387635",  "sdftsfat", 87.267,  743.4)
        )
        personlistview.adapter=PersonAdepter(this,array)
    }*/

}