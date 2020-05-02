package com.example.interventionroom

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
       val date=findViewById<EditText>(R.id.editsearch)


        floatingButtonAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@MainActivity, AddIntervention::class.java)
                startActivity(intent)
}})
        search.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Toast.makeText(applicationContext, date.text.toString(), Toast.LENGTH_LONG).show()
                getInterventionBydate(date.text.toString())
            }})
        getIntervention()
    }
    private fun getIntervention() {
        class GetIntervention : AsyncTask<Void, Void, List<Intervention>>() {

            override fun doInBackground(vararg voids: Void): List<Intervention> {
                return DatabaseClient
                    .getInstance(applicationContext)
                    .appDatabase
                    .InterventionDao()
                    .getAll()
            }

            override fun onPostExecute(Intervention: List<Intervention>) {
                super.onPostExecute(Intervention)
                val adapter = InterventionAdapter(this@MainActivity, Intervention)
                recyclerView!!.adapter = adapter
            }
        }

        val gt = GetIntervention()
        gt.execute()
    }
    private fun getInterventionBydate(date:String) {
        class GetInterventionBydate : AsyncTask<Void, Void, List<Intervention>>() {

            override fun doInBackground(vararg voids: Void): List<Intervention> {
                return DatabaseClient
                    .getInstance(applicationContext)
                    .appDatabase
                    .InterventionDao()
                    .getIntervention(date)
            }

            override fun onPostExecute(Intervention: List<Intervention>) {
                super.onPostExecute(Intervention)
                val adapter = InterventionAdapter(this@MainActivity, Intervention)
                recyclerView!!.adapter = adapter
            }
        }

        val gt = GetInterventionBydate()
        gt.execute()
    }

}
