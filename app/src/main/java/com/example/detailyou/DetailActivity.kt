package com.example.detailyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.detailyou.databinding.ActivityDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DetailActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        Log.d(LoginActivity.TAG, "onCreate: ${auth.currentUser?.email}")

        setViews()


        binding.btnSignOut?.setOnClickListener {
            auth.signOut()
            Log.d(LoginActivity.TAG, "onCreate: ${auth.currentUser?.displayName}")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setViews() {
        binding.apply {
            userName.text = auth.currentUser?.displayName
            userEmail.text = if(auth.currentUser?.email!=null && auth.currentUser?.email!!.isNotBlank()) auth.currentUser?.email else "no email"
            userNumber.text = if(auth.currentUser?.phoneNumber!=null && auth.currentUser?.phoneNumber!!.isNotBlank()) auth.currentUser?.phoneNumber else "no phone Number"
            Glide.with(this@DetailActivity).load(auth.currentUser?.photoUrl).into(userImage)
        }
    }


}