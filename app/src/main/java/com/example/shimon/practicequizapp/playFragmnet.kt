package com.example.shimon.practicequizapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.shimon.practicequizapp.databinding.FragmentPlayFragmnetBinding
import com.example.shimon.practicequizapp.databinding.FragmentResultBinding
import java.util.concurrent.TimeUnit

class playFragmnet : Fragment() {

    lateinit var binding: FragmentPlayFragmnetBinding



    var quizList = listOf<Quiz>(

        Quiz("Victory Day of Bangladesh","12 december","14 december","26 march","16 december","16 december"),
        Quiz("Independent Day of Bangladesh","12 december","14 december","26 march","16 december","26 march"),
        Quiz("International Mother Language Day of Bangladesh","21 February","14 december","26 march","16 december","21 February"),
        Quiz("Worker Day of The world","12 december","1 May","26 march","16 december","1 May")
    )

    var updateQuestionNo = 1
    var countDownTimer : CountDownTimer? = null
    var miliLestSec = 30000L
    var index = 0
    var hasFinished = false
    var skip = -1
    var correct = 0
    var wrong=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayFragmnetBinding.inflate(layoutInflater,container,false)


        binding.questionNumberTV.text = "$updateQuestionNo/${quizList.size}"
        startCountDownTimer()
        initQuestion()

        binding.nextQuestionBTN.setOnClickListener {
            showNextQuestion()
        }



        return binding.root
    }
//question set
    private fun initQuestion() {
        val quiz = quizList[index]

        binding.apply {
            questionTV.text = quiz.question
            option1.text =quiz.option1
            option2.text = quiz.option2
            option3.text = quiz.option3
            option4.text = quiz.option4
        }


    }

    private fun showNextQuestion(){
        checkAnswer()
        binding.apply {
            //question update after clicking next BTN
            if (updateQuestionNo<quizList.size){
                updateQuestionNo++
                questionNumberTV.text = "$updateQuestionNo/${quizList.size}"
            }
            if (index<=quizList.size-1){

                initQuestion()
            }
            //when all question was finished then this function will work
            else{
               hasFinished = true
            }
//clear all ans when new question was came
            radioGroup.clearCheck()
        }

}

    private fun checkAnswer() {

        binding.apply {

          if ( radioGroup.checkedRadioButtonId == -1) {
              skip++
          }
            else{
                val checkButton = view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
              var checkAnswer = checkButton?.text.toString()

              if (checkAnswer==quizList[index].rightAnswer){
                  correct++
                  scoreTV.text = "Score : $correct"
                  countDownTimer?.cancel()
                  showAlertDialog("Right Answer")
              }
              else{
                 wrong++
                 countDownTimer?.cancel()
                  showAlertDialog("Wrong Answer")
              }

          }
            if (index <=quizList.size-1){
                index++
            }
            else{
                showAlertDialog("Finish")
            }



        }
    }

    private fun startCountDownTimer(){
        countDownTimer = object:CountDownTimer(miliLestSec,1000){
            override fun onTick(millisUntilFinished: Long) {
                miliLestSec = millisUntilFinished
                val second = TimeUnit.MILLISECONDS.toSeconds(miliLestSec).toInt()

                binding.timerTV.text = "Time Left : $second"

            }

            override fun onFinish() {
                showNextQuestion()
            }

        }.start()
    }

    fun showAlertDialog(message : String){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(message)

        builder.setPositiveButton("ok", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (message=="Finish"){
                    countDownTimer?.cancel()


                    val bundle = Bundle()
                    bundle.putString("skip", skip.toString())
                    bundle.putString("right",correct.toString())
                    bundle.putString("wrong", wrong.toString())
                    bundle.putString("numOfQuestion", quizList.size.toString())
                    findNavController().navigate(R.id.action_playFragmnet_to_resultFragment,bundle)

                }

                countDownTimer?.start()
            }

        })

        var alertDialog = builder.create()
        alertDialog.show()


    }

}
