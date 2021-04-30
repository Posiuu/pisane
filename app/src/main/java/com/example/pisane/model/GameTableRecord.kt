package com.example.pisane.model

object RecordType{
    const val UNKNOWN = -1
    const val HEADER = 0
    const val YELLOW = 1
    const val WHITE = 2
}

class GameTableRecord() {
    var type = RecordType.UNKNOWN
    lateinit var name: String
    lateinit var score: String
    lateinit var totalScore: String

    constructor(nameHeader: String, scoreHeader: String, totalScoreHeader: String) : this() {
        this.type = RecordType.HEADER
        this.name = nameHeader
        this.score = scoreHeader
        this.totalScore = totalScoreHeader
    }

    constructor(name: String, recordType: Int) : this() {
        this.type = recordType
        this.name = name
        this.score = ""
        this.totalScore = ""
    }
}