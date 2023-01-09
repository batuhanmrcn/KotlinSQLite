package com.batuhanmercan.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.sql.SQLData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try{

            val myDataBase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)

            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY ,name VARCHAR, age INT)")

            //myDataBase.execSQL("INSERT INTO musicians (name,age) VALUES ('James',50)")
            // myDataBase.execSQL("INSERT INTO musicians (name,age) VALUES ('Batuhan',22)")

            myDataBase.execSQL("UPDATE musicians SET age = 61 WHERE name ='Lars'")

           val cursor = myDataBase.rawQuery("SELECT * FROM musicians",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")


            while (cursor.moveToNext()){
                println("Name:  " + cursor.getString(nameIx))
                println("Age:  "+ cursor.getInt(ageIx))
                println("Id:  "+ cursor.getInt(idIx))
            }
                cursor.close()

        } catch (e : Exception){
            e.printStackTrace()
        }





    }
}