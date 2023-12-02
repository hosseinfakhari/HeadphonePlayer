package com.shinogati.headphoneplayer.ui.activity

import android.content.Context
import com.shinogati.headphoneplayer.domain.model.AudioMetadata

sealed class AudioPlayerEvent {
    data class InitAudio(val audio: AudioMetadata, val context: Context) : AudioPlayerEvent()

    data class Seek(val position: Float): AudioPlayerEvent()

    object Play: AudioPlayerEvent()

    object Pause: AudioPlayerEvent()

    object Stop: AudioPlayerEvent()

    object LoadMedias: AudioPlayerEvent()
}