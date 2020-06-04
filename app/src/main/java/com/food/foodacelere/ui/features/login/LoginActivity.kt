package com.food.foodacelere.ui.features.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.food.foodacelere.R
import com.food.foodacelere.ui.app.*
import com.food.foodacelere.ui.app.Validations.Companion.validateEmail
import com.food.foodacelere.ui.app.Validations.Companion.validatePassword
import com.food.foodacelere.ui.features.recovery.ForgotPasswordActivity
import com.food.foodacelere.ui.features.signup.CadastroActivity
import com.food.foodacelere.ui.features.store.HomeStoreActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel
    lateinit var mSharedPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mSharedPreferences = SecurityPreferences(this)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setupObservable()



        lblRegistrar.setOnClickListener {
            val intent = Intent(
                this,
                CadastroActivity::class.java
            )
            startActivity(intent)
        }

        lblEsqueceuSenha.setOnClickListener {
            val intent = Intent(
                this,
                ForgotPasswordActivity::class.java
            )
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            hideKeyboard()
            validateLogin()
        }
    }

    private fun validateLogin() {
        val email = txtLogin.text.toString()
        val passw = txtLoginSenha.text.toString()

        var validToLogin = true

        if (!email.validateEmail()) {
            txtLogin.error = "Email inválido"
            validToLogin = false
        }
        if (!passw.validatePassword()) {
            txtLoginSenha.error = "Senha inválida"
            validToLogin = false
        }

        if (validToLogin) {
            showLoading()
            viewModel.signIn(email, passw)
        }
    }

    private fun hideKeyboard() {
        val view = btnLogin
        if(view != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showLoading() {
        loadingLoginProgress.visibility = View.VISIBLE
        btnLogin.alpha = 0f
        txtLogin.isEnabled = false
        txtLoginSenha.isEnabled = false
    }

    private fun hideLoading() {
        loadingLoginProgress.visibility = View.GONE
        btnLogin.alpha = 1f
        txtLogin.isEnabled = true
        txtLoginSenha.isEnabled = true
    }


    private fun setupObservable() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is LoadingData -> {

                }
                is SuccessData -> {
                    storeStringsOnSharedPreferences(it.user)
                    callHomeStore()
                }
                is ErrorData -> {
                    Toast.makeText(
                        this,
                        "Ops! Usuário ou senha inválidos, verifique seus dados e tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                    hideLoading()

                }
            }
        })
    }

    private fun callHomeStore() {
        val intent = Intent(this, HomeStoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun storeStringsOnSharedPreferences(user: User?) {
        user?.let {
            mSharedPreferences.storeString(Constants.KEY.USER_ID, user.userId)
            mSharedPreferences.storeString(Constants.KEY.USER_EMAIL, user.email)
        }
    }


}

