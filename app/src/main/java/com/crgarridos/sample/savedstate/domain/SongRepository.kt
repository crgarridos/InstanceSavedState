package com.crgarridos.sample.savedstate.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

class SongRepository @Inject constructor() {
    fun getSongsByName(name: String): List<Song> {
        return generateSequence(1, Long::inc)
            .map(::Song)
            .take(150)
            .filter { name in it.id.toString() }
            .toList()
    }
}

@Parcelize
data class Song(val id: Long): Parcelable