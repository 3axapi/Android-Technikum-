package com.example.kotlinactivity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var gameResult: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rollDice: Button = findViewById(R.id.play_button);
        val resetButton: Button = findViewById(R.id.button_reset);
        val rollResultTV: TextView = findViewById(R.id.dice_result_textview)
        val gameResultTV: TextView = findViewById(R.id.game_result_textview)
        // val questionIView: ImageView = findViewById(R.id.question_imageview);
        val firstDice: ImageView = findViewById(R.id.first_dice_imageview);
        val secondDice: ImageView = findViewById(R.id.second_dice_imageview);
        val thirdDice: ImageView = findViewById(R.id.third_dice_imageview);
        val fourthDice: ImageView = findViewById(R.id.fourth_dice_imageview);
        val fifthDice: ImageView = findViewById(R.id.fifth_dice_imageview);
        // val sixthDice: ImageView = findViewById(R.id.sixth.dice_imageview);

        val diceImages = mapOf(
            1 to R.drawable.one,
            2 to R.drawable.two,
            3 to R.drawable.three,
            4 to R.drawable.four,
            5 to R.drawable.five,
            6 to R.drawable.six
        )

        rollDice.setOnClickListener {
            val randomNumberList: List<Int> = List(5){(1..6).random()}
            val (first, second, third, fourth, fifth) = randomNumberList

            firstDice.setImageResource(diceImages[first] ?: R.drawable.question)
            secondDice.setImageResource(diceImages[second] ?: R.drawable.question)
            thirdDice.setImageResource(diceImages[third] ?: R.drawable.question)
            fourthDice.setImageResource(diceImages[fourth] ?: R.drawable.question)
            fifthDice.setImageResource(diceImages[fifth] ?: R.drawable.question)

            val numberOccurence = mutableMapOf<Int, Int>()
            for (num in randomNumberList) {
                numberOccurence[num] = numberOccurence.getOrDefault(num, 0) + 1
            }

            val roll_result = numberOccurence
                .filter { it.value > 1 }
                .map { it.key * it.value }
                .sum()
            rollResultTV.text = " ${roll_result}"
            gameResult += roll_result
            gameResultTV.text = " ${gameResult}"
        }

        resetButton.setOnClickListener {
            gameResult = 0
            rollResultTV.text = " 0"
            gameResultTV.text = " 0"

            firstDice.setImageResource(R.drawable.question)
            secondDice.setImageResource(R.drawable.question)
            thirdDice.setImageResource(R.drawable.question)
            fourthDice.setImageResource(R.drawable.question)
            fifthDice.setImageResource(R.drawable.question)
        }

    }
}