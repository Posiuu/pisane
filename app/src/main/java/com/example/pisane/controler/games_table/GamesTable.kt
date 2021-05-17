package com.example.pisane.controler.games_table

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.pisane.data.game_table_records.*
import com.example.pisane.model.game_table_record.*

class GamesTable() {
    val data = gameTableScoresList
    private var totalScoreIndex = 1
    var tableTotalScore = 0

    fun updateWithPossibleScores(possibleScores: List<Int>) {
        for (i in data.indices) {
            val record = data[i]
            val isHeaderRow = record.type == RecordType.HEADER
            if (isHeaderRow) {
                continue
            }

            // i - 1 because we have headers row in scoresTableData
            val possibleScore = possibleScores[i - 1]

            val isGameCrossedOut = record.score == CROSSED_OUT
            val isGamePlayed = (record.score != NOT_PLAYED) && (record.score != CROSSED_OUT)
            val isGameWithNewPossibleScore = !isGamePlayed && (0 < possibleScore)
            val isGamePossibleToCrossOut = !isGamePlayed && (possibleScore == 0)
            if (isGameCrossedOut) {
                record.redLineVisibility = VISIBLE

                record.scoreVisibility = INVISIBLE
                record.possibleScoreVisibility = INVISIBLE
                record.redCrossVisibility = INVISIBLE
                record.greedArrowVisibility = INVISIBLE
            }
            else if (isGamePlayed) {
                record.scoreVisibility = VISIBLE

                record.possibleScoreVisibility = INVISIBLE
                record.redLineVisibility = INVISIBLE
                record.redCrossVisibility = INVISIBLE
                record.greedArrowVisibility = INVISIBLE
            }
            else if (isGameWithNewPossibleScore) {
                record.possibleScore = possibleScore
                record.possibleScoreVisibility = VISIBLE
                record.greedArrowVisibility = VISIBLE

                record.scoreVisibility = INVISIBLE
                record.redLineVisibility = INVISIBLE
                record.redCrossVisibility = INVISIBLE
            }
            else if (isGamePossibleToCrossOut) {
                record.possibleScore = possibleScore
                record.redCrossVisibility = VISIBLE

                record.scoreVisibility = INVISIBLE
                record.possibleScoreVisibility = INVISIBLE
                record.redLineVisibility = INVISIBLE
                record.greedArrowVisibility = INVISIBLE
            }
        }
    }

    fun chooseGameScore(position: Int) {
        val record = data[position]
        record.score = record.possibleScore.toString()

        tableTotalScore += record.possibleScore
        data[totalScoreIndex].totalScore = tableTotalScore.toString()
        data[totalScoreIndex].totalScoreVisibility = VISIBLE

        totalScoreIndex++
    }
}