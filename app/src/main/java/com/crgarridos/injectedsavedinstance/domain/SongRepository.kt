package com.crgarridos.injectedsavedinstance.domain
class SongRepository {
    fun getSongs(): List<Song> {
        return generateSequence(1, Long::inc)
            .map(::Song)
            .take(50)
            .toList()
    }
}

data class Song(val id: Long)