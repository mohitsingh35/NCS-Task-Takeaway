package com.ncs.ncstakeawaytask

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncs.ncstakeawaytask.databinding.ActivityMainBinding
import com.ncs.ncstakeawaytask.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var userList = mutableListOf<User>()
    private lateinit var adapter: Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        PrefManager.initialize(this)
        binding.btnLogout.setOnClickListener {
            PrefManager.setcurrentUserdetails(User(email = "", password = "", name = "", bio = "", phoneNum = "", detailsAdded = false))
            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            intent.putExtra("nav", "chooser")
            startActivity(intent)
            finish()
        }

        userList=PrefManager.getAllUsers().toMutableList()
        setUpRecyclerView(userList)
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int){
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    private fun setUpRecyclerView(list:List<User>){
        val recyclerView=binding.recyclerView
        adapter = Adapter(list.toMutableList())
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    private fun filter(text: String) {
        val filteredList = mutableListOf<User>()
        for (user in userList) {
            if (user.name.contains(text, ignoreCase = true) || user.email.contains(text,ignoreCase = true) || user.phoneNum.contains(text,ignoreCase = true)) {
                filteredList.add(user)
            }
        }
        adapter.filterList(filteredList)
    }
}