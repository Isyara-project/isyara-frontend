package com.application.isyara.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.application.isyara.R
import com.application.isyara.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                binding.emailLayout.error = "Email harus diisi"
                return@setOnClickListener
            } else {
                binding.emailLayout.error = null
            }

            Toast.makeText(
                requireContext(),
                "Kode OTP telah dikirim ke email Anda",
                Toast.LENGTH_SHORT
            ).show()
            binding.otpLayout.visibility = View.VISIBLE
            binding.verifyButton.visibility = View.VISIBLE
        }

        binding.verifyButton.setOnClickListener {
            val otp = binding.otpEditText.text.toString().trim()

            if (otp.isEmpty()) {
                binding.otpLayout.error = "Kode OTP harus diisi"
                return@setOnClickListener
            } else {
                binding.otpLayout.error = null
            }

            if (otp == "123456") {
                Toast.makeText(requireContext(), "OTP Benar!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Kode OTP salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}