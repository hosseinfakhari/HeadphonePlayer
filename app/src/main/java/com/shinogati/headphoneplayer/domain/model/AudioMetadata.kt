package com.shinogati.headphoneplayer.domain.model

import android.graphics.Bitmap
import android.net.Uri
import java.time.Duration

data class AudioMetadata(
    val contentUri: Uri,
    val songId: Long,
    val cover: Bitmap?,
    val songTitle: String,
    val artist: String,
    val duration: Int
) {
    companion object {
        fun emptyMetadata() : AudioMetadata {
            return AudioMetadata(
                contentUri = Uri.EMPTY,
                songId = 0L,
                cover = null,
                songTitle = "",
                artist = "",
                duration = 0
            )
        }
    }
}