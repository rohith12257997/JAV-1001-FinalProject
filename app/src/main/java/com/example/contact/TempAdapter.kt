package com.example.contact

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*













class TempAdapter(
    private var contactsList:ArrayList<ContactModel>,
    private var context:Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.contacts_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    fun displayAdapter(displayedList: ArrayList<ContactModel>) {
        contactsList = displayedList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = contactsList[position].name
        holder.number.text = contactsList[position].number
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, AddContact::class.java)
            intent.putExtra("title","Edit Contact")
            intent.putExtra("forEdit","true")
            intent.putExtra("name",contactsList[position].name)
            intent.putExtra("number",contactsList[position].number)
            intent.putExtra("save","SAVE")
            val pos=position.toString()
            intent.putExtra("pos",pos)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            v.context.startActivity(intent)
        }
        holder.delete.setOnClickListener {
            contactsList.removeAt(position)
            val sharedPreference=SharedPreference(context)
            sharedPreference.setStateList(contactsList)
            notifyDataSetChanged()
        }
    }
}
class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var name= itemView.findViewById<TextView>(R.id.contactName)!!
    var number= itemView.findViewById<TextView>(R.id.contactNumber)!!
    val delete= itemView.findViewById<ImageView>(R.id.delete)!!


}