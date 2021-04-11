package io.github.yogiseralia.weatherapp.preferences

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import io.github.yogiseralia.weatherapp.Preferences
import java.io.InputStream
import java.io.OutputStream

//object PreferencesSerializer : Serializer<Preferences> {
//    override val defaultValue: Preferences = Preferences.getDefaultInstance()
//
//    override suspend fun readFrom(input: InputStream): Preferences {
//        try {
//            return Preferences.parseFrom(input)
//        } catch (exception: InvalidProtocolBufferException) {
//            throw CorruptionException("Cannot read proto.", exception)
//        }
//    }
//
//    override suspend fun writeTo(
//        t: Preferences,
//        output: OutputStream
//    ) = t.writeTo(output)
//}
//
//// with Proto DataStore
//val Context.PreferencesDataStore: DataStore<Preferences> by dataStore(
//    fileName = "preferences.pb",
//    serializer = PreferencesSerializer
//)
