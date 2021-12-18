package com.example.contact

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*




class AddContact : AppCompatActivity() {
    private var contactsList: ArrayList<ContactModel>? = null
    private lateinit var name:String
    private lateinit var number:String
    private lateinit var pos:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        supportActionBar?.hide()
        // checking whether it is for edit or add contact

        val forEdit = intent.getStringExtra("forEdit")
        val add:Button=findViewById(R.id.addBtn)
        val sharedPreference = SharedPreference(applicationContext)
         val nameField:EditText=findViewById(R.id.nameField)
         val numberField:EditText=findViewById(R.id.numberField)
        // changing text based on edit/add contact
        if(forEdit=="true"){
        val titleText = intent.getStringExtra("title")
         name = intent.getStringExtra("name")!!
         number = intent.getStringExtra("number")!!
        val buttonText = intent.getStringExtra("save")
        val position = intent.getStringExtra("pos")
        val title:TextView=findViewById(R.id.addTitle)
        title.text=titleText
        nameField.setText(name)
        numberField.setText(number)
            add.text=buttonText
            pos=position!!
        }
        add.setOnClickListener {
            if(forEdit=="true"){
                // checking name and number field has value
                if(nameField.text.toString()==name&&numberField.text.toString()==number){
                    Toast.makeText(applicationContext,"Please update Name and Phone Number",Toast.LENGTH_LONG).show()
                }else{
                    addContact(nameField,numberField,sharedPreference,true,)
                }
            }else{
           addContact(nameField, numberField,sharedPreference,false)}
        }
    }
 // storing contact to shared preference
    private fun addContact(
        nameField: EditText,
        numberField: EditText,
        sharedPreference: SharedPreference,forEdit:Boolean
    ) {
        if(nameField.text.isNotEmpty()&&numberField.text.isNotEmpty()){
            contactsList=HomePage.contactsList;
            if(forEdit){
                contactsList!![pos.toInt()].name=nameField.text.toString()
                contactsList!![pos.toInt()].number=numberField.text.toString()
            }else{
            contactsList!!.add(ContactModel(nameField.text.toString(),numberField.text.toString()))
            }
            sharedPreference.setStateList(contactsList!!)
            val intent=Intent(applicationContext,HomePage::class.java)
            startActivity(intent)
            finish()
    }
}
}