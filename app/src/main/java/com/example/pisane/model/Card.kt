package com.example.pisane.model

object CardColor {
    const val DIAMOND = 0
    const val HEART = 1
    const val SPADE = 2
    const val CLUB = 3
    const val JOKER = 4
}

object CardFigure {
    const val F2 = 0
    const val F3 = 1
    const val F4 = 2
    const val F5 = 3
    const val F6 = 4
    const val F7 = 5
    const val F8 = 6
    const val F9 = 7
    const val F10 = 8
    const val FJ = 9
    const val FQ = 10
    const val FK = 11
    const val FA = 12
    const val JOKER = 13
}

class Card(val color: Int, val figure: Int, val value: Int, val image: Int)