package com.shinogati.headphoneplayer.domain.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.shinogati.headphoneplayer.domain.model.AudioMetadata

interface HeadphonePlayerRepository {
    suspend fun getAudios(): List<AudioMetadata>
    suspend fun  loadCoverBitmap(context: Context, uri: Uri): Bitmap?
}