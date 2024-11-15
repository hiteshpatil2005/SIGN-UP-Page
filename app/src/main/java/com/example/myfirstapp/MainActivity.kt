package com.example.myfirstapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var mobileNumberInput: EditText
    lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        usernameInput = findViewById(R.id.username_input)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        mobileNumberInput = findViewById(R.id.mobileNumberInput)
        submitBtn = findViewById(R.id.submitButton)

        submitBtn.setOnClickListener {

            val username = usernameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val phone = mobileNumberInput.text.toString().trim()

            // Validate inputs
            if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank() && phone.isNotBlank()) {

                if (isValidEmail(email) && isValidPhone(phone)) {
                    // Display user information except password for security
                    Toast.makeText(this, "Username: $username\nEmail: $email\nPhone: $phone", Toast.LENGTH_LONG).show()
                } else {
                    if (!isValidEmail(email)) {
                        Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                    }
                    if (!isValidPhone(phone)) {
                        Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Prompt the user to fill all fields
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validate email format (simple regex)
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Validate phone number (contains only digits and has 10 digits)
    private fun isValidPhone(phone: String): Boolean {
        return phone.length == 10 && phone.all { it.isDigit() }
    }
}
