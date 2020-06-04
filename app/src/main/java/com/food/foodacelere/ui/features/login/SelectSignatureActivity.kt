package com.food.foodacelere.ui.features.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.food.foodacelere.R
import com.food.foodacelere.ui.features.signup.CadastroViewModel
import com.food.foodacelere.ui.features.store.HomeStoreActivity
import kotlinx.android.synthetic.main.activity_select_signature.*

class SelectSignatureActivity : AppCompatActivity() {

    lateinit var viewModel: SelectSignatureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_signature)

        viewModel = ViewModelProvider(this).get(SelectSignatureViewModel::class.java)
        viewModel.isLogedIn()
        setObservable()
        setupUserProfile()
        setupRestaurantProfile()
    }

    private fun setObservable() {
        viewModel.isUserLoged.observe(this, Observer {
            when (it.status) {
                StateLog.Companion.STATE.LOGDED -> {
                    Log.i("aspk", "USUÁRIO LOGADO")
                    callStoreHome()
                }
            }
        })
    }

    private fun callStoreHome() {
        val intent = Intent(this, HomeStoreActivity::class.java)
        startActivity(intent)
        finish()
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

    private fun navigateToUserFlow() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRestaurantFlow() {
        Toast.makeText(this, "Perfil para restaurantes em breve!", Toast.LENGTH_SHORT).show()
    }
}
