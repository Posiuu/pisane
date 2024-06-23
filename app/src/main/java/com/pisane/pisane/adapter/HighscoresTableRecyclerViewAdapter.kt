package com.pisane.pisane.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.pisane.pisane.R
import com.pisane.pisane.model.Highscore
import com.pisane.pisane.model.HighscoreRecordType

class HighscoresTableRecyclerViewAdapter(
        private val context: Context,
        private val highscoresList: List<Highscore>
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class RecordViewHolder(recordView: View) :
            RecyclerView.ViewHolder(recordView){
        val placeTextView: TextView = recordView.findViewById(R.id.hrPlaceTextView)
        val nickTextView: TextView = recordView.findViewById(R.id.hrNickTextView)
        val scoreTextView: TextView = recordView.findViewById(R.id.hrScoreTextView)

        fun bind(position: Int) {
            val record = highscoresList[position]
            placeTextView.text = record.place
            nickTextView.text = record.nick
            scoreTextView.text = record.score
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RecyclerView.ViewHolder {
        return when (viewType) {
            HighscoreRecordType.HEADER -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.highscores_headers_row, parent, false))
            HighscoreRecordType.DARK -> RecordViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.highscores_single_row, parent, false))
            HighscoreRecordType.BRIGHT -> RecordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.highscores_single_row2, parent, false))
            else -> throw NoSuchElementException("Unknown game row view type.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecordViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return highscoresList.size
    }

    override fun getItemViewType(position: Int): Int {
        return highscoresList[position].type
    }
}