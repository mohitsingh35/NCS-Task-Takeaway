package com.ncs.ncstakeawaytask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ncs.ncstakeawaytask.databinding.FragmentLoginBinding
import com.ncs.ncstakeawaytask.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    lateinit var binding:FragmentSignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefManager.initialize(requireContext())
        setUpViews()
    }

    private fun setUpViews(){
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_signupscreenFragment_to_authchooserFragment)
        }
        binding.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_signupscreenFragment_to_loginFragment)
        }
        binding.btnSignup.setOnClickListener {
            val result=validate(binding.etEmail.text.toString(),binding.etPass.text.toString(),binding.etConfirmPass.text.toString())
            when(result){
                0->{
                    val user=User(email =  binding.etEmail.text.toString(),
                        password = binding.etConfirmPass.text.toString(),
                        detailsAdded = false,
                        name = "",
                        bio = "",
                        phoneNum = "")

                    PrefManager.setcurrentUserdetails(user)
                    Toast.makeText(requireContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signupscreenFragment_to_detailsEnterFragment)

                }
                1->{
                    binding.errorText.visibility=View.VISIBLE
                    binding.errorText.text="Email provided is not in valid format"
                }
                2->{
                    binding.errorText.visibility=View.VISIBLE
                    binding.errorText.text="Password length should be atleast 6"
                }
                3->{
                    binding.errorText.visibility=View.VISIBLE
                    binding.errorText.text="Both the passwords didn't match"
                }
                4->{
                    binding.errorText.visibility=View.VISIBLE
                    binding.errorText.text="This Email is already in use. Please Login"
                }
            }
        }
    }

    private fun validate(email:String,pass:String,confirmPass:String):Int{
        return if (!isEmail(email)){
            1
        } else if (pass.length <6 && confirmPass.length < 6){
            2
        } else if (pass!=confirmPass){
            3
        }
        else if (PrefManager.getUserDetails(email)!=null){
            4
        }
        else{
            0
        }
    }

    fun isEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }



}