package com.kauvito.contactlist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kauvito.contactlist.DetailActivity.Companion.EXTRA_CONTACT

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    private val rvList: RecyclerView by lazy{
        findViewById<RecyclerView>(R.id.rv_list)
    }

    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        fetchListContact()
        bindViews()
    }

    //Preferencias de usuario
    private fun fetchListContact(){
        val list =  arrayListOf(
            Contacts(
                "Kauan",
                "(19)96895 3251"
            ),
            Contacts(
                "Catarina",
                "(19)96895 3251"
            )
        )

        getInstanceSharedPreferences().edit{
            val json = Gson().toJson(list)
            putString("contacts", json )
            commit()
        }
    }

    private fun getInstanceSharedPreferences() : SharedPreferences{
        return getSharedPreferences("com.kauvito.contactlist.PREFERENCES", Context.MODE_PRIVATE)
    }


    //DrawerLayout
    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    //Adapter
    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        val list = getListContacts()
        adapter.updateList(list)
    }

    //Converte a lista para json
    private fun getListContacts(): List<Contacts>{
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnsType = object : TypeToken<List<Contacts>>() {}.type
        return Gson().fromJson(list, turnsType)
    }
    //

    private fun updateList(list: List<Contacts>) {
        adapter.updateList(
            arrayListOf(
                Contacts(
                    "Kauan",
                    "(19)96895 3251"
                ),
                Contacts(
                    "Catarina",
                    "(19)96895 3251"
                )
            )
        )
    }


    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    //Menus, criar no repositorio, opção menu em res
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu_1 -> {
                showToast("Item de menu 1")
                return true
            }
            R.id.item_menu_2 -> {
                showToast("Item de menu 2")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //inicia a DetailActivity
    override fun clickItemContact(contact: Contacts){
        val intent = Intent(this , DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }

}