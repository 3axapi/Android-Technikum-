package com.example.kotlinactivity

fun main() {
    var numberOfDice: Int

    do  {
        println("Ile kostek chcesz rzucić? (4-10)")
        numberOfDice = readln().toInt()
    } while (numberOfDice !in 3..10)

    val diceSides = arrayOf(1, 2, 3, 4, 5, 6)
    var score: Int = 0

    while (true) {
        val diceRolls = List(numberOfDice) { diceSides.random() }
        diceRolls.forEachIndexed{index, roll ->
            println("Kostka ${index+1}: $roll")
        }

        val rollCounts = diceRolls.groupingBy { it }.eachCount()
        score += rollCounts.filter { it.value >= 2 }
            .map { it.key * it.value }
            .sum()

        println("Liczba uzyskanych punktów: $score")
        println("Jeszcze raz? (t/n)")
        val response = readln()
        if (response == "n") break
    }
}