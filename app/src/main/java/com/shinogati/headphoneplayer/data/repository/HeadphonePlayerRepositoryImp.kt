package com.shinogati.headphoneplayer.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.shinogati.headphoneplayer.domain.model.AudioMetadata
import com.shinogati.headphoneplayer.domain.repository.HeadphonePlayerRepository
import com.shinogati.headphoneplayer.util.audio.MetadataHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeadphonePlayerRepositoryImp @Inject constructor(
    private val metadataHelper: MetadataHelper
) : HeadphonePlayerRepository {

    override suspend fun getAudios(): List<AudioMetadata> {
        return withContext(Dispatchers.IO) {
            metadataHelper.getAudios()
        }
    }

    override suspend fun loadCoverBitmap(context: Context, uri: Uri): Bitmap? {
        return withContext(Dispatchers.IO) {
            metadataHelper.getAlbumArt(
                context = context,
                uri = uri
            )
        }
    }
}