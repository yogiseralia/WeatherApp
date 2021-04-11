package io.github.yogiseralia.weatherapp.preferences

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import io.github.yogiseralia.weatherapp.Preferences
import io.github.yogiseralia.weatherapp.R
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PreferenceFragment : Fragment() {
    private val TAG = "PreferenceFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_preference, container, false)
        lifecycleScope.launch {
        /*    try {
                val prefernces: Preferences? = context?.PreferencesDataStore?.data?.first()
                Log.d(TAG, "reading datastore = temp " + prefernces?.tempUnit?.number)
            } catch (e: Exception) {
            }*/
            // You should also handle IOExceptions here.
        }
        return view
    }
}