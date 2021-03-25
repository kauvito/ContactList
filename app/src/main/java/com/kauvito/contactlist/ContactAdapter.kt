package com.kauvito.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(var listener: ClickItemContactListener) :
    RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list : MutableList<Contacts> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }


    //Obtem os item o array e popula a tela(recyclerView)
    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //atualiza a lista e notifica o apadter que modificou a lista usada
    fun updateList(list: List<Contacts>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    //Classe responsavel em gerenciar cada itens da lista
    class ContactAdapterViewHolder(itemView: View, var list:List<Contacts>, var listener: ClickItemContactListener)
        : RecyclerView.ViewHolder(itemView)
    {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phoneNumber)
        private val ivPhotograph: ImageView = itemView.findViewById(R.id.iv_photograph)

        //evento do toque
        init {
            itemView.setOnClickListener{
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contacts){
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}