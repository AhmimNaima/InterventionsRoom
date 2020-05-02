package com.example.interventionroom

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update.*

class UpdateInterventionActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val i = intent.getSerializableExtra("Intervention") as Intervention
        loadIntervention(i)
        button_update.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View) {
                Toast.makeText(getApplicationContext(), "Mis à jour", Toast.LENGTH_LONG).show()
                updateIntervention(i)
            }
        })


        button_delete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                deleteIntervention(i)
            }
        })
    }

    private fun loadIntervention(i:Intervention) {
        editNom.setText(i.nom)
        editNum.setText(i.numero)
        editType.setText(i.type)
        editdate.setText(i.date)
    }


    private fun updateIntervention(i: Intervention) {
        val numVal = editNum!!.text.toString().trim { it <= ' ' }
        val nomVal = editNom!!.text.toString().trim { it <= ' ' }
        val typeVal = editType!!.text.toString().trim { it <= ' ' }
        val dateVal = editdate!!.text.toString().trim { it <= ' ' }


        class UpdateIntervention : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                i.numero = numVal
                i.nom = nomVal
                i.type = typeVal
                i.date = dateVal
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .InterventionDao()
                    .update(i)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this@UpdateInterventionActivity, MainActivity::class.java))
            }
        }

        val st = UpdateIntervention()
        st.execute()
    }

    private fun deleteIntervention(i: Intervention) {
        class DeleteIntervention: AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                DatabaseClient.getInstance(applicationContext).appDatabase
                    .InterventionDao()
                    .delete(i)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                
                startActivity(Intent(this@UpdateInterventionActivity, MainActivity::class.java))
            }
        }

        val dt = DeleteIntervention()
        dt.execute()

    }

}
