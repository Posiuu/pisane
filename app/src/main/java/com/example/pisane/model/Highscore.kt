package com.example.pisane.model

object HighscoreRecordType{
    const val UNKNOWN = -1
    const val HEADER = 0
    const val DARK = 1
    const val BRIGHT = 2
}

class Highscore() {
    var type = HighscoreRecordType.UNKNOWN
    lateinit var place: String
    lateinit var nick: String
    lateinit var score: String

    constructor(recordType: Int, place: String, nick: String, score: String) : this() {
        this.type = recordType
        this.place = place
        this.nick = nick
        this.score = score
    }

    fun copy(): Highscore {
        val newRecord: Highscore

        newRecord = Highscore(this.type, this.place, this.nick, this.score)

        return newRecord
    }
}