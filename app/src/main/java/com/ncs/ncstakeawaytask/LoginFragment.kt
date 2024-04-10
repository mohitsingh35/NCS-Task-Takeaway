package com.ncs.ncstakeawaytask

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ncs.ncstakeawaytask.databinding.FragmentAuthChooserBinding
import com.ncs.ncstakeawaytask.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefManager.initialize(requireContext())
        setUpViews()
    }

    private fun setUpViews(){
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_authchooserFragment)
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpScreenFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val pass=binding.etPass.text.toString()

            val user=PrefManager.getUserDetails(email)
            if (user!=null && pass==user.password){
                PrefManager.setcurrentUserdetails(user)
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(),MainActivity::class.java))
                requireActivity().finish()
            }
            else{
                Toast.makeText(requireContext(), "Invalid Login Details", Toast.LENGTH_SHORT).show()
            }

        }

    }







}