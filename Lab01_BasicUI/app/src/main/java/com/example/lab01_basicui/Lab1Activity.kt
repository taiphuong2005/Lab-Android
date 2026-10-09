package com.example.lab01_basicui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab01_basicui.databinding.ActivityLab1Binding

class Lab1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLab1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLab1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nút quay lại
        binding.ivBack.setOnClickListener {
            finish()
        }

        // Nút Đăng ký
        binding.btnRegister.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_success), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
