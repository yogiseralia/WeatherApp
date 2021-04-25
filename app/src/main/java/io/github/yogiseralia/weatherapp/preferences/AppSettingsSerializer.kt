package io.github.yogiseralia.weatherapp.preferences

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import io.github.yogiseralia.weatherapp.AppSettings
import java.io.InputStream
import java.io.OutputStream

class AppSettingsSerializer : Serializer<AppSettings> {
    override val defaultValue: AppSettings
        get() = AppSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppSettings {
        try {
            return AppSettings.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Can't read proto", e)
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        return t.writeTo(output)
    }
}

// with Proto DataStore
val Context.appDataStore: DataStore<AppSettings> by dataStore(
        fileName = "app_settings.pb",
        serializer = AppSettingsSerializer()
)