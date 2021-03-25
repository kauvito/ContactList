package com.kauvito.contactlist

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity(){
    private var contact: Contacts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_details)

        initToolbar()
        getExtras()
        bindViews()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun getExtras(){
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    private fun bindViews(){
        findViewById<TextView>(R.id.tv_name).text = contact?.name
        findViewById<TextView>(R.id.tv_phoneNumber).text = contact?.phone
    }

    companion object {
        const val EXTRA_CONTACT:String = "EXTRA_CONTACT"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}