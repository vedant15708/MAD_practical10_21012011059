package com.example.mad_practical10_21012011059

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class PersonAdepter(context: Context,val personArray:ArrayList<Person>):ArrayAdapter<Person>(context,0,personArray) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(R.layout.person_item,parent,false)
        view.findViewById<TextView>(R.id.name).text=personArray[position].name
        view.findViewById<TextView>(R.id.mobileno).text=personArray[position].phoneNO
        view.findViewById<TextView>(R.id.email).text=personArray[position].emailId
        view.findViewById<TextView>(R.id.address).text=personArray[position].address
        view.findViewById<MaterialButton>(R.id.btn_1).setOnClickListener {
            Intent(context, MapsActivity::class.java).putExtra("object", personArray[position])
                .apply {
                    context.startActivity(this)
                }
        }
        return view
    }

}