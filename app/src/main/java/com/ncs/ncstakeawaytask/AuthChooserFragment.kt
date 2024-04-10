package com.ncs.ncstakeawaytask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ncs.ncstakeawaytask.databinding.FragmentAuthChooserBinding


class AuthChooserFragment : Fragment() {


    lateinit var binding: FragmentAuthChooserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthChooserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    private fun setUpViews() {


        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_authchooserFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_authchooserFragment_to_signUpScreenFragment)
        }

    }

}