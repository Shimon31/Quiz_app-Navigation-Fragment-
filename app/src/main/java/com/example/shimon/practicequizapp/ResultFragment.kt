package com.example.shimon.practicequizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shimon.practicequizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater,container,false)

        //get value
        val skip = requireArguments().getString("skip", "0")
        val corerct = requireArguments().getString("right","0")
        val wrong = requireArguments().getString("wrong","0")
        val numOfQuestion = requireArguments().getString("numOfQuestion","0")



        binding.showResult.text = "Number Of Question :$numOfQuestion\n"+
                "Skip: $skip\n"+
                "Correct: $corerct\n"+
                "Wrong: $wrong\n"




        return binding.root




    }

}