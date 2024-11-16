package com.application.isyara.ui.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.isyara.MainActivity
import com.application.isyara.R
import com.application.isyara.data.User
import com.application.isyara.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        loadSavedCredentials()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyUsers = listOf(
            User("user1@gmail.com", "password123"),
            User("user2@gmail.com", "password456")
        )

        val progressBar = binding.progressBar

        setupTextWatchers()
        setupLoginButton(dummyUsers, progressBar)
        setupNavigationListeners()
    }

    private fun loadSavedCredentials() {
        val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val savedEmailOrUsername = sharedPref.getString("email_or_username", "")
        val savedPassword = sharedPref.getString("password", "")
        val rememberMeChecked =
            !savedEmailOrUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()

        if (rememberMeChecked) {
            binding.emailOrUsernameEditText.setText(savedEmailOrUsername)
            binding.passwordEditText.setText(savedPassword)
        }
        binding.rememberMeCheckBox.isChecked = rememberMeChecked
    }

    private fun setupTextWatchers() {
        binding.emailOrUsernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.emailOrUsernameLayout.error =
                    if (s.isNullOrEmpty()) "Email atau username harus diisi" else null
            }
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.passwordLayout.error = "Password harus diisi"
                    binding.passwordLayout.endIconMode = TextInputLayout.END_ICON_NONE
                } else {
                    binding.passwordLayout.error = null
                    binding.passwordLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                }
            }
        })
    }


    private fun setupLoginButton(dummyUsers: List<User>, progressBar: View) {
        binding.loginButton.setOnClickListener {
            val emailOrUsername = binding.emailOrUsernameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (validateInputs(emailOrUsername, password)) {
                progressBar.visibility = View.VISIBLE

                val user =
                    dummyUsers.find { it.emailOrUsername == emailOrUsername && it.password == password }
                progressBar.visibility = View.GONE

                if (user != null) {
                    handleSuccessfulLogin(emailOrUsername, password)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Email/username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun validateInputs(emailOrUsername: String, password: String): Boolean {
        var isValid = true

        if (emailOrUsername.isEmpty()) {
            binding.emailOrUsernameLayout.error = "Email atau username harus diisi"
            isValid = false
        } else {
            binding.emailOrUsernameLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordLayout.error = "Password harus diisi"
            isValid = false
        } else {
            binding.passwordLayout.error = null
        }

        return isValid
    }

    private fun handleSuccessfulLogin(emailOrUsername: String, password: String) {
        val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val rememberMeChecked = binding.rememberMeCheckBox.isChecked

        with(sharedPref.edit()) {
            if (rememberMeChecked) {
                putString("email_or_username", emailOrUsername)
                putString("password", password)
            } else {
                remove("email_or_username")
                remove("password")
            }
            apply()
        }

        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
        Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()
    }

    private fun setupNavigationListeners() {
        binding.forgotPasswordTextView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ForgotPasswordFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.registerTextView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
