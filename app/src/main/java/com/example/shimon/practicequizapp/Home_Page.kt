package com.example.shimon.practicequizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shimon.practicequizapp.databinding.FragmentHomePageBinding
import java.util.zip.Inflater


class Home_Page : Fragment() {
    lateinit var binding:FragmentHomePageBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater,container,false)

        binding.playBTN.setOnClickListener(){

            findNavController().navigate(R.id.action_home_Page_to_playFragmnet2)
        }



        return binding.root
    }


}