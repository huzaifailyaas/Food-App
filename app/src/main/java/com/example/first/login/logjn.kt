package com.example.first.login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.first.R
import com.example.first.main.frontpage
import com.google.firebase.auth.FirebaseAuth

class logjn : AppCompatActivity() {
    private lateinit var login:Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logjn)
        email=findViewById(R.id.emailLogin)
        password=findViewById(R.id.passwordLogin)
        login=findViewById(R.id.btnSignIn)

        login.setOnClickListener {
            val email=email.text.toString()
            val password=password.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this , "Enter data", Toast.LENGTH_SHORT).show()

            }
            else if(!email.contains("@gmail.com")){
                Toast.makeText(this,"enter correct email", Toast.LENGTH_SHORT).show()
            }
            else if(password.length<6){
                Toast.makeText(this,"enter strong password", Toast.LENGTH_SHORT).show()
            }
            else{
                login(email,password)
            }
        }

    }

    private fun login(useremail: String, userpass: String) {
        dialog = ProgressDialog(this)
        dialog.setTitle("Please wait")
        dialog.setMessage("Signing you in!")
        dialog.show()
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(useremail, userpass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dialog.dismiss()
                val obj = Intent(this,frontpage::class.java)
                startActivity(obj)
                Toast.makeText(this, "Welcome back user", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()

        }
    }
}