package com.ncs.ncstakeawaytask

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import com.ncs.ncstakeawaytask.databinding.FragmentDetailsEnterBinding
import com.ncs.ncstakeawaytask.databinding.FragmentLoginBinding


class DetailsEnterFragment : Fragment() {

    lateinit var binding: FragmentDetailsEnterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsEnterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        disableBackPress()
    }

    private fun setUpViews(){
        binding.btnSignup.setOnClickListener {
            val name=binding.etName.text.toString()
            val bio=binding.etBio.text.toString()
            val phNum=binding.etPhone.text.toString()

            if (name.isNotEmpty() && bio.isNotEmpty() && phNum.isNotEmpty()){
                if (phNum.length!=10){
                    Toast.makeText(requireContext(), "Phone Number is Invalid", Toast.LENGTH_SHORT).show()
                }
                else{
                    val user=PrefManager.getcurrentUserdetails()
                    val newUser=User(email =  user.email,
                        password = user.password,
                        detailsAdded = true,
                        name = name,
                        bio = bio,
                        phoneNum = phNum)

                    PrefManager.setcurrentUserdetails(newUser)
                    PrefManager.setUserDetails(newUser)
                    Toast.makeText(requireContext(), "You are registered now!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(),MainActivity::class.java))
                    requireActivity().finish()
                }
            }
            else{
                Toast.makeText(requireContext(), "All the fields are required", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun disableBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Error can't go back", Toast.LENGTH_SHORT).show()
        }
    }

}