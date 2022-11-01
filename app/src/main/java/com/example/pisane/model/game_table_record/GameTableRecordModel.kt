package com.example.pisane.model.game_table_record

import android.view.View.INVISIBLE
import android.view.View.VISIBLE

object RecordType{
    const val UNKNOWN = -1
    const val HEADER = 0
    const val YELLOW = 1
    const val WHITE = 2
}

const val CROSSED_OUT = "0"
const val NOT_PLAYED = ""

class GameTableRecord() {
    var type = RecordType.UNKNOWN
    lateinit var name: String
    var score = NOT_PLAYED
    var possibleScore = 0
    var totalScore = ""

    var scoreVisibility = INVISIBLE
    var possibleScoreVisibility = INVISIBLE
    var totalScoreVisibility = INVISIBLE
    var redLineVisibility = INVISIBLE
    var redCrossVisibility = INVISIBLE
    var greedArrowVisibility = INVISIBLE

    constructor(nameHeader: String, scoreHeader: String, totalScoreHeader: String) : this() {
        this.type = RecordType.HEADER
        this.name = nameHeader
        this.score = scoreHeader
        this.totalScore = totalScoreHeader

        this.scoreVisibility = VISIBLE
        this.totalScoreVisibility = VISIBLE
    }

    constructor(name: String, recordType: Int) : this() {
        this.type = recordType
        this.name = name
    }

    fun copy(): GameTableRecord {
        val newRecord: GameTableRecord

        if (this.type == RecordType.HEADER){
            newRecord = GameTableRecord(this.name, this.score, this.totalScore)
        }
        else {
            newRecord = GameTableRecord(this.name, this.type)
        }

        return newRecord
    }
}