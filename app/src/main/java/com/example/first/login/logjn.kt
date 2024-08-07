package com.example.first.login

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.first.R
import com.example.first.main.frontpage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class logjn : AppCompatActivity() {
    private lateinit var login:Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: ProgressDialog
    private lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logjn)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this , gso)

        findViewById<Button>(R.id.btnGoogle).setOnClickListener {
            signInGoogle()
        }

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


    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent = Intent(this , frontpage::class.java)
                intent.putExtra("email" , account.email)
//                intent.putExtra("name" , account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()

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