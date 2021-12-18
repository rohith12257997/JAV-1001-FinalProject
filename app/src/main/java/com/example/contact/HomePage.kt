package com.example.contact

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomePage : AppCompatActivity() {
    companion object{
         var contactsList: ArrayList<ContactModel>? = null

    }

    private lateinit var tempList: ArrayList<ContactModel>
    private var contactRV: RecyclerView? = null
    private var contactRVAdapter: TempAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        supportActionBar?.hide()
        val sharedPreference = SharedPreference(applicationContext)
        val add:TextView=findViewById(R.id.add)
        val search:EditText=findViewById(R.id.searchBar)
        var progressBar:ProgressBar=findViewById(R.id.progressBar)
        progressBar.visibility=View.VISIBLE
        contactsList= ArrayList()
        contactRV=findViewById(R.id.contactList)
        //getting list from local storage
        val list= sharedPreference.getStateList()
        if(list!=null&&list.isNotEmpty()){
            contactsList=list
        }
        progressBar.visibility=View.GONE

        //search
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(text: CharSequence, i: Int, i1: Int, i2: Int) {
                if(search.text.isEmpty()){
                    setList()
                }
                tempList= ArrayList()
                for(j in contactsList!!.indices){
                    if(contactsList!![j].name.contains(text)||
                        contactsList!![j].number.contains(text)){
                        tempList.add(contactsList!![j])
                    }
                }
                contactRVAdapter= TempAdapter(tempList,applicationContext)
                contactRV!!.adapter=contactRVAdapter
            }

            override fun afterTextChanged(editable: Editable) {
            }
        })
        setList()
        add.setOnClickListener {
            val intent=Intent(applicationContext,AddContact::class.java)
            intent.putExtra("name","Add Contact")
            startActivity(intent)
        }
    }
 // implementing list to adapter
    private fun setList() {
    contactRV!!.layoutManager=LinearLayoutManager(applicationContext)
        contactRVAdapter= TempAdapter(contactsList!!,applicationContext)
        contactRV!!.adapter=contactRVAdapter
    }

}

