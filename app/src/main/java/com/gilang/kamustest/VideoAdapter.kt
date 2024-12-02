package com.gilang.kamustest

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val videoUrls: List<String>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUrl = videoUrls[position]
        holder.bind(videoUrl)
    }

    override fun getItemCount(): Int = videoUrls.size

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoUrlTextView: TextView = itemView.findViewById(R.id.video_url)
        private val playButton: Button = itemView.findViewById(R.id.playButton)

        fun bind(videoUrl: String) {
            videoUrlTextView.text = getFileNameFromUrl(videoUrl)

            playButton.setOnClickListener {
                val context = itemView.context

                try {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(videoUrl)
                        setDataAndType(Uri.parse(videoUrl), "video/*")
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }

                    // Start the activity and check for available video players
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // Display an error message if no video player is available
                    Toast.makeText(context, "Unable to play the video. Check the URL or install a video player.", Toast.LENGTH_LONG).show()
                }
            }
        }

        private fun getFileNameFromUrl(url: String): String {
            return url.substringAfterLast("/")
        }
    }
}
