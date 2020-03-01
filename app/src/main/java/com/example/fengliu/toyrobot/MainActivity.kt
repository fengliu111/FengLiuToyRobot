package com.example.fengliu.toyrobot

import Robot
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var robot = Robot()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goButton.setOnClickListener{
            runButtonClick()
        }
    }

    private fun runButtonClick(){
        //hide keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

        val commandStr = inputTextView.text.toString().trim()
        if(!commandStr.equals("")) {
            clearAllImages()
            outputTextView.setText("")
            val commands = commandStr.split(" ")
            var isPlaced = false
            var hasPlaceCommand = false
            var isEnd = false
            for(command in commands){
                var action = command.trim()
                var direction = arrayOfNulls<String>(3)
                if (hasPlaceCommand && (command.toUpperCase().contains("NORTH") || command.toUpperCase().contains(
                        "EAST"
                    ) || command.toUpperCase().contains("WEST") || command.toUpperCase().contains(
                        "SOUTH"
                    ))
                ) {
                    action = "DIRECTION"
                    direction = command.split(",").toTypedArray()
                }

                when (action.toUpperCase()) {
                    "PLACE" -> {
                        hasPlaceCommand = true
                    }
                    "DIRECTION" -> {
                        if (direction.size == 3) {
                            try {
                                robot.place(
                                    direction[0].toString().trim().toInt(),
                                    direction[1].toString().trim().toInt(),
                                    direction[2].toString().trim()
                                )
                                isPlaced = true
                            } catch (e: Exception) {
                                printOutput(e.message.toString())
                                isEnd = true
                            }
                        } else {
                            printOutput("Please specify correct direction")
                            isEnd = true
                        }
                    }
                    "MOVE" -> {
                        try {
                            if (isPlaced) {
                                robot.move()
                            } else {
                                printOutput("Please place robot on the table firstly")
                                isEnd = true
                            }
                        } catch (e: Exception) {
                            printOutput(e.message.toString())
                            isEnd = true
                        }
                    }
                    "LEFT" -> {
                        if (isPlaced) {
                            robot.left()
                        } else {
                            printOutput("Please place robot on the table firstly")
                            isEnd = true
                        }
                    }
                    "RIGHT" -> {
                        if (isPlaced) {
                            robot.right()
                        } else {
                            printOutput("Please place robot on the table firstly")
                            isEnd = true
                        }
                    }
                    "REPORT" -> {
                        if (isPlaced) {
                            printOutput(robot.report())
                        } else {
                            printOutput("Please place robot on the table firstly")
                        }
                        isEnd = true
                    }
                    else -> {
                        printOutput("Wrong command, command must be \"PLACE\", \"MOVE\", \"LEFT\", \"RIGHT\", or \"REPORT\"")
                        isEnd = true
                    }
                }

                if (isEnd) {
                    break
                }

                if (!action.toUpperCase().equals("PLACE")) {
                    updateRobot()
                }
            }
        }

    }

    private fun clearAllImages(){
        for(x in 0..robot.getTable().getWidth()){
            for(y in 0..robot.getTable().getHeight()){
                val imageView = findViewById<ImageView>(resources.getIdentifier("image_${x}_${y}", "id", packageName))
                imageView.setImageDrawable(null)
            }
        }
    }

    private fun updateRobot(){
        clearAllImages()
        val imageView = findViewById<ImageView>(resources.getIdentifier("image_${robot.getPosX()}_${robot.getPosY()}", "id", packageName))
        imageView.setImageResource(resources.getIdentifier("robot_${robot.getFaceTo().toLowerCase()}", "drawable", packageName))
    }

    private fun printOutput(outputText: String){
        outputTextView.setText(outputText)
    }
}
