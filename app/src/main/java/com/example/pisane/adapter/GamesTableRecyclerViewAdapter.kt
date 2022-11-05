package com.example.pisane.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.pisane.R
import com.example.pisane.model.game_table_record.*

class GamesTableRecyclerViewAdapter(
        private val context: Context,
        private val gameTableRecordsList: List<GameTableRecord>,
        private val chooseGameButtonHandling: (Int) -> Unit
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class RecordViewHolder(recordView: View) :
            RecyclerView.ViewHolder(recordView){
        val nameTextView: TextView = recordView.findViewById(R.id.grGameNameTextView)
        val redLineImageView: ImageView = recordView.findViewById(R.id.grRedLineImageView)
        val scoreTextView: TextView = recordView.findViewById(R.id.grGameScoreTextView)
        val possibleScoreTextView: TextView = recordView.findViewById(R.id.grPossibleScoreTextView)
        val redCrossImageButton: ImageButton = recordView.findViewById(R.id.grRedCrossImageButton)
        val greenArrowImageButton: ImageButton = recordView.findViewById(R.id.grGreenArrowImageButton)
        val totalScoreTextView: TextView = recordView.findViewById(R.id.grTotalScoreTextView)

        fun bind(position: Int) {
            val record = gameTableRecordsList[position]
            nameTextView.text = record.name
            scoreTextView.text = record.score
            possibleScoreTextView.text = record.possibleScore.toString()
            totalScoreTextView.text = record.totalScore

            scoreTextView.visibility = record.scoreVisibility
            possibleScoreTextView.visibility = record.possibleScoreVisibility
            totalScoreTextView.visibility = record.totalScoreVisibility
            redLineImageView.visibility = record.redLineVisibility
            redCrossImageButton.visibility = record.redCrossVisibility
            greenArrowImageButton.visibility = record.greedArrowVisibility

            greenArrowImageButton.setOnClickListener {
                chooseGameButtonHandling(position)
            }

            redCrossImageButton.setOnClickListener {
                chooseGameButtonHandling(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RecyclerView.ViewHolder {
        return when (viewType) {
            GameRecordType.HEADER -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.game_table_headers_row, parent, false))
            GameRecordType.YELLOW -> RecordViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.game_table_single_game_row, parent, false))
            GameRecordType.WHITE -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.game_table_single_game_row2, parent, false))
            else -> throw NoSuchElementException("Unknown game row view type.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecordViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return gameTableRecordsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return gameTableRecordsList[position].type
    }
}