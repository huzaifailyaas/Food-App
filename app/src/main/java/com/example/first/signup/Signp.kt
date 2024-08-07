package com.example.first.signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.first.Info.info
import com.example.first.R
import com.example.first.main.frontpage
import com.example.first.modelclass.DetailClass
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signp : AppCompatActivity() {

    private lateinit var signup: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmpassword: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var dialog: ProgressDialog
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signp)


        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this , gso)

        findViewById<Button>(R.id.btnGoogle).setOnClickListener {
            signInGoogle()
        }


        email = findViewById(R.id.emailSignup)
        password = findViewById(R.id.passwordSignup)
        confirmpassword = findViewById(R.id.cpassSignup)
        signup = findViewById(R.id.btnSignUp)

        signup.setOnClickListener {
            var email = email.text.toString()
            var password = password.text.toString()
            var confirmpassword = confirmpassword.text.toString()
            if (email.trim().isEmpty() || password.trim().isEmpty() || confirmpassword.trim().isEmpty()) {
                Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show()

            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())) {
                Toast.makeText(this, "enter correct gmail", Toast.LENGTH_SHORT).show()

            } else if (password.length < 6) {
                Toast.makeText(this, "enter strong password", Toast.LENGTH_SHORT).show()
            } else if (!password.equals(confirmpassword)) {
                Toast.makeText(this, "password does not match", Toast.LENGTH_SHORT).show()

            } else {
                signup(email, password)
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
//                     intent.putExtra("email" , account.email)
//                     intent.putExtra("name" , account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun signup(email: String, password: String) {
        auth = FirebaseAuth.getInstance()

        dialog = ProgressDialog(this)
        dialog.setTitle("Please wait")
        dialog.setMessage("we are creating your account")
        dialog.show()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                dialog.dismiss()

                val obj1= Intent(this,info::class.java)
                startActivity(obj1)

                AddDatatoDatabse(email, password)
                Toast.makeText(this, "Login again", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }

    fun AddDatatoDatabse(email:String ,password:String) {
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("Edify")
        val obj = DetailClass(email, "12345678")
        var ID = ref.push().key.toString()
        ref.child("info").child(ID).setValue(obj).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Added", Toast.LENGTH_LONG).show()

            }
        }.addOnFailureListener {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()

        }

    }
}