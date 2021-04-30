package com.example.pisane.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pisane.R
import com.example.pisane.model.GameTableRecord
import com.example.pisane.model.RecordType

class GamesTableRecyclerViewAdapter(
        private val context: Context,
        private val dataSet: List<GameTableRecord>
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class RecordViewHolder(recordView: View) :
            RecyclerView.ViewHolder(recordView){
        val nameTextView: TextView = recordView.findViewById(R.id.gameNameTextView)
        val scoreTextView: TextView = recordView.findViewById(R.id.gameScoreTextView)
        val totalScoreTextView: TextView = recordView.findViewById(R.id.totalScoreTextView)

        fun bind(position: Int){
            var record = dataSet[position]
            nameTextView.text = record.name
            scoreTextView.text = record.score
            totalScoreTextView.text = record.totalScore
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RecyclerView.ViewHolder {
        return when (viewType) {
            RecordType.HEADER -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.game_table_headers_row, parent, false))
            RecordType.YELLOW -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.game_table_single_game_row, parent, false))
            RecordType.WHITE -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.game_table_single_game_row2, parent, false))
            else -> throw NoSuchElementException("Unknown game row view type.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecordViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type
    }
}