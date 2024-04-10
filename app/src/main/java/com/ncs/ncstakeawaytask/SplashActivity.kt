package com.ncs.ncstakeawaytask

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ncs.ncstakeawaytask.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        PrefManager.initialize(this)
        val currentUser=PrefManager.getcurrentUserdetails()
        CoroutineScope(Dispatchers.Main).launch{
            delay(2000L)
            if (currentUser.email==""){
                val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                intent.putExtra("nav", "chooser")
                startActivity(intent)
                finish()
            }
            else if (!currentUser.detailsAdded){
                val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                intent.putExtra("nav", "details")
                startActivity(intent)
                finish()

            }
            else {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()

            }
        }

    }
}