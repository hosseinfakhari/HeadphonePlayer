package com.shinogati.headphoneplayer.ui.activity

import com.shinogati.headphoneplayer.domain.model.AudioMetadata

data class AudioPlayerState(
    val isLoading: Boolean = false,
    val audios: List<AudioMetadata> = emptyList(),
    val selectedAudio: AudioMetadata = AudioMetadata.emptyMetadata(),
    val isPlaying: Boolean = false
)
