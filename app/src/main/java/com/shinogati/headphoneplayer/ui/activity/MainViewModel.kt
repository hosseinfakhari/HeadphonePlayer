package com.shinogati.headphoneplayer.ui.activity

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinogati.headphoneplayer.domain.model.AudioMetadata
import com.shinogati.headphoneplayer.domain.repository.HeadphonePlayerRepository
import com.shinogati.headphoneplayer.util.audio.VisualizerData
import com.shinogati.headphoneplayer.util.audio.VisualizerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HeadphonePlayerRepository
) : ViewModel() {
    private var _player: MediaPlayer? = null
    private var _visualizerHelper = VisualizerHelper()

    private var _state by mutableStateOf(value = AudioPlayerState())
    val state: AudioPlayerState
        get() = _state

    private val _visualizerData = mutableStateOf<VisualizerData>(VisualizerData.emptyVisualizerData())
    private val visualizerData: State<VisualizerData>
        get() = _visualizerData

    init {
        loadMedias()
    }

    fun onEvent(event: AudioPlayerEvent) {
        when(event) {
            is AudioPlayerEvent.InitAudio -> initAudio(
                context = event.context,
                audio = event.audio
            )

            is AudioPlayerEvent.Seek -> seek(event.position)

            AudioPlayerEvent.LoadMedias -> loadMedias()

            AudioPlayerEvent.Pause -> pause()

            AudioPlayerEvent.Play -> play()

            AudioPlayerEvent.Stop -> stop()
        }
    }

    private fun loadMedias() {
        viewModelScope.launch {
            _state = _state.copy(isLoading = true)
            val audios = mutableListOf<AudioMetadata>()
            audios.addAll(prepareAudios())
            _state = _state.copy(audios = audios, isLoading = false)
        }
    }

    private suspend fun prepareAudios(): List<AudioMetadata> {
        return repository.getAudios().map { audio ->
            val artist = if (audio.artist.contains("<unknown>"))
                "Uknown Artist" else audio.artist
            audio.copy(artist = artist)
        }
    }

    private fun initAudio(audio: AudioMetadata, context: Context) {
        viewModelScope.launch {
            _state = _state.copy(isLoading = true)

            delay(800)

            val cover = repository.loadCoverBitmap(context = context, uri = audio.contentUri)

            _state = _state.copy(selectedAudio = audio.copy(cover = cover))

            _player = MediaPlayer().apply {
                setDataSource(context, audio.contentUri)
                prepare()
            }

            _state = _state.copy(isLoading = false)
        }
    }

    private fun play() {
        _state = _state.copy(isPlaying = true)
        _player?.start()
        _player?.run {
            _visualizerHelper.start(
                audioSessionId = audioSessionId,
                onData = {data ->
                    _visualizerData.value = data
                }
            )
        }
    }

    private fun pause() {
        _state = _state.copy(isPlaying = false)
        _visualizerHelper.stop()
        _player?.pause()
    }

    private fun stop() {
        _visualizerHelper.stop()
        _player?.stop()
        _player?.reset()
        _player?.release()
        _state = _state.copy(isPlaying = false)
        _player = null
    }

    private fun seek(position: Float) {
        _player?.run {
            seekTo(position.toInt())
        }
    }
}