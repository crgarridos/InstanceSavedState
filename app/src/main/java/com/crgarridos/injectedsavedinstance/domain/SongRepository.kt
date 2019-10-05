package com.crgarridos.injectedsavedinstance.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class SongRepository {
    fun getSongs(): List<Song> {
        return generateSequence(1, Long::inc)
            .map(::Song)
            .take(50)
            .toList()
    }
}

@Parcelize
data class Song(val id: Long): Parcelable