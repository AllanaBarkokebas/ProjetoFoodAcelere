package com.food.foodacelere.ui.app

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class Service {

    private val auth = FirebaseAuth.getInstance()
    private val fire = FirebaseFirestore.getInstance()


    fun resisterUserEmailAndPassword(
        userEmail: String,
        userPwd: String,
        success: (result: FirebaseUser?) -> Unit,
        error: (message: String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(userEmail, userPwd)
            .addOnSuccessListener {
                success(it.user)
            }.addOnFailureListener {
                error(it.message)

            }
    }

    fun saveAdditionalUserInfoOnDataBase(
        newUser: User,
        success: () -> Unit,
        error: (message: String?) -> Unit
    ) {
        auth.uid?.let { id ->
            fire.collection("users")
                .document(id)
                .set(newUser)
                .addOnSuccessListener {
                    success()

                }
                .addOnFailureListener {
                    error(it.message)

                }
        }
    }

    fun isUserLogedOnFirebase(): Boolean {
        return auth.currentUser != null
    }

    fun signInUser(
        email: String,
        password: String,
        success: (FirebaseUser?) -> Unit,
        error: (String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    success(it.result?.user)

                }

            }
            .addOnFailureListener {
                error(it.message)
                signOutFirebase()

                Log.i("aspk","Error: ${it.message}")
                //implementar mesagem com error code em portugues
                //val a  = (it as FirebaseAuthException).errorCode
            }


    }

    fun signOutFirebase() {
        auth.signOut()
    }

}