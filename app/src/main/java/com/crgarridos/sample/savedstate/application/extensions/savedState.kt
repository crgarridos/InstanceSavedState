package com.crgarridos.sample.savedstate.application.extensions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
inline fun <T> savedState(key: String, handle: SavedStateHandle) = SavedStateDelegate<T>(key, handle)

class SavedStateDelegate<T>(
    private val key: String,
    private val handle: SavedStateHandle
) : ReadWriteProperty<ViewModel, T?> {

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): T? =
        handle.get<T>(key)

    override fun setValue(thisRef: ViewModel, property: KProperty<*>, value: T?) =
        handle.set(key, value)
}