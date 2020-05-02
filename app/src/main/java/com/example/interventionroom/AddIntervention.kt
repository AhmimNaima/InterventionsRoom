package com.example.interventionroom

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_addintervention.*

class AddIntervention : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.interventionroom.R.layout.activity_addintervention)
        button_save.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        val numVal = num!!.text.toString().trim { it <= ' ' }
        val nomVal = nom!!.text.toString().trim { it <= ' ' }
        val typeVal = type!!.text.toString().trim { it <= ' ' }
        val dateVal = date!!.text.toString().trim{it<=' ' }

        if (numVal.isEmpty()) {
            num!!.error = "Task required"
            num!!.requestFocus()
            return
        }

        if (nomVal.isEmpty()) {
            nom!!.error = "Desc required"
            nom!!.requestFocus()
            return
        }

        if (typeVal.isEmpty()) {
            type!!.error = "Finish by required"
            type!!.requestFocus()
            return
        }
        if (dateVal.isEmpty()) {
            type!!.error = "Finish by required"
            type!!.requestFocus()
            return
        }

        class SaveIntervention : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {

                //creating a task
                val intervention = Intervention(numVal, nomVal, typeVal,dateVal)

                //adding to database
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .InterventionDao()
                    .insert(intervention)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
            }
        }

        val st = SaveIntervention()
        st.execute()
    }

}