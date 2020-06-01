package com.food.foodacelere.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.food.foodacelere.R
import kotlinx.android.synthetic.main.activity_select_signature.*

class SelectSignatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_signature)

        setupUserProfile()
        setupRestaurantProfile()
    }

    private fun setupUserProfile() {
        btnUserProfile.setOnClickListener {
            navigateToUserFlow()
        }
    }

    private fun setupRestaurantProfile() {
        btnRestaurantProfile.setOnClickListener {
            navigateToRestaurantFlow()
        }
    }

    private fun navigateToUserFlow(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRestaurantFlow(){
       Toast.makeText(this,"Perfil para restaurantes em breve!",Toast.LENGTH_SHORT).show()
    }
}
