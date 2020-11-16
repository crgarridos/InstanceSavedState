package com.crgarridos.sample.savedstate.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

class SongRepository {
    suspend fun getSongsByName(name: String): List<Song> {
        delay(200)
        val sanitizedName = name.toUpperCase(Locale.getDefault())

        return generateSequence(1, Long::inc)
            .map { Song(it, Random(it).nextString(10)) }
            .take(250)
            .filter { sanitizedName in it.name || sanitizedName in it.id.toString() }
            .toList()
    }
}

@Parcelize
data class Song(val id: Long, val name: String) : Parcelable

private fun Random.nextString(length: Int):String = String(CharArray(length) { nextChar() })
private fun Random.nextChar(): Char = nextInt('A'.toInt(), 'Z'.toInt()).toChar()