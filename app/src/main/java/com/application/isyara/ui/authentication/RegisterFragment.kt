package com.application.isyara.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.application.isyara.R
import com.application.isyara.databinding.FragmentRegisterBinding
import com.application.isyara.utils.isEmailValid
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = binding.progressBar

        binding.usernameEditText.addTextChangedListener {
            validateUsername(binding.usernameEditText.text.toString())
        }

        binding.emailEditText.addTextChangedListener {
            validateEmail(binding.emailEditText.text.toString())
        }

        binding.passwordEditText.addTextChangedListener {
            validatePassword(binding.passwordEditText.text.toString())
            updatePasswordIconVisibility()
        }

        binding.confirmPasswordEditText.addTextChangedListener {
            validateConfirmPassword(binding.confirmPasswordEditText.text.toString())
            updatePasswordIconVisibility()
        }

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

            val isFormValid = validateUsername(username) &&
                    validateEmail(email) &&
                    validatePassword(password) &&
                    validateConfirmPassword(confirmPassword)

            if (isFormValid) {
                progressBar.visibility = View.VISIBLE

                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT).show()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Registrasi gagal, silahkan perbaiki data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.loginTextView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun validateUsername(username: String): Boolean {
        val usernamePattern = "^[a-zA-Z0-9_]{6,20}$".toRegex()
        return when {
            username.isEmpty() -> {
                binding.usernameLayout.error = "Username harus diisi"
                false
            }

            !username.matches(usernamePattern) -> {
                binding.usernameLayout.error =
                    "Username tidak valid. Harus 6-20 karakter tanpa spasi."
                false
            }

            else -> {
                binding.usernameLayout.error = null
                true
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                binding.emailLayout.error = "Email harus diisi"
                false
            }

            !email.isEmailValid() -> {
                binding.emailLayout.error = "Format email tidak valid"
                false
            }

            else -> {
                binding.emailLayout.error = null
                true
            }
        }
    }

    private fun validatePassword(password: String): Boolean {
        val passwordPattern =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$".toRegex()
        return when {
            password.isEmpty() -> {
                binding.passwordLayout.error = "Password harus diisi"
                false
            }

            !password.matches(passwordPattern) -> {
                binding.passwordLayout.error =
                    "Password minimal 8 karakter, mengandung huruf besar, kecil, angka, dan simbol"
                false
            }

            else -> {
                binding.passwordLayout.error = null
                true
            }
        }
    }

    private fun validateConfirmPassword(confirmPassword: String): Boolean {
        val password = binding.passwordEditText.text.toString()
        return when {
            confirmPassword.isEmpty() -> {
                binding.confirmPasswordLayout.error = "Konfirmasi password harus diisi"
                false
            }

            confirmPassword != password -> {
                binding.confirmPasswordLayout.error = "Password dan konfirmasi password tidak sama"
                false
            }

            else -> {
                binding.confirmPasswordLayout.error = null
                true
            }
        }
    }

    private fun updatePasswordIconVisibility() {
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()

        binding.passwordLayout.endIconMode =
            if (password.isNotEmpty() && validatePassword(password)) {
                TextInputLayout.END_ICON_PASSWORD_TOGGLE
            } else {
                TextInputLayout.END_ICON_NONE
            }

        binding.confirmPasswordLayout.endIconMode =
            if (confirmPassword.isNotEmpty() && validateConfirmPassword(confirmPassword)) {
                TextInputLayout.END_ICON_PASSWORD_TOGGLE
            } else {
                TextInputLayout.END_ICON_NONE
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
