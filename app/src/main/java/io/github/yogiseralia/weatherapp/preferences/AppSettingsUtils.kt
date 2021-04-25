package io.github.yogiseralia.weatherapp.preferences

import android.content.Context
import android.content.pm.PackageManager

fun Context.getAppVersion(): String {
    return try {
        val pInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        "No version found"
    }
}
