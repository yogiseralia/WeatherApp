package io.github.yogiseralia.weatherapp.preferences

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import io.github.yogiseralia.weatherapp.R
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class PreferenceFragment : Fragment() {
    private val TAG = "PreferenceFragment"
    lateinit var preferenceViewModel: PreferenceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_preference, container, false)
        setupProfileCardUI(view)
        setupAboutSection(view)

        return view
    }

    private fun setupProfileCardUI(view: View) {
        val profile_card = view.findViewById<MaterialCardView>(R.id.cardvw_profile_details)
//        val profile_pic = profile_card.findViewById<ImageView>(R.id.imgvw_profile_pic)
//        val profile_name = profile_card.findViewById<TextView>(R.id.txtvw_profile_name)
//        val profile_email = profile_card.findViewById<TextView>(R.id.txtvw_profile_email)
        val btn_sign_in_or_out = profile_card.findViewById<Button>(R.id.btn_sign_in_or_out)
        btn_sign_in_or_out.isEnabled = false
        profile_card.visibility = VISIBLE
    }

    private fun setupAboutSection(view: View) {
        setupAppVesionUI(view)
        setupAuthorUI(view)
        setupWeatherSourceUI(view)
        setupNewsSourceUI(view)
    }

    private fun setupAppVesionUI(view: View) {
        val txtvw_app_version = view.findViewById<TextView>(R.id.txtvw_app_version)
        val appVersionString =
            StringBuilder(txtvw_app_version.text).append(this.context?.getAppVersion()).toString()
        txtvw_app_version.text = appVersionString
    }

    private fun setupNewsSourceUI(view: View) {
        val txtvw_news_source = view.findViewById<TextView>(R.id.txtvw_news_source)
        txtvw_news_source.visibility = INVISIBLE
    }

    @SuppressLint("ResourceAsColor")
    private fun setupAuthorUI(view: View): String {
        val txtvw_authors = view.findViewById<TextView>(R.id.txtvw_authors)
        val authorsString =
            StringBuilder(txtvw_authors.text).append("Yogesh Seralia").toString()
        val spAuthorsString = SpannableString(authorsString)
        spAuthorsString.setSpan(UnderlineSpan(), 10, authorsString.length, 0)
        spAuthorsString.setSpan(
            ForegroundColorSpan(R.color.colorAccent),
            10,
            authorsString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtvw_authors.text = spAuthorsString
        txtvw_authors.setOnClickListener {
            val url = "https://github.com/yogiseralia"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        return authorsString
    }

    @SuppressLint("ResourceAsColor")
    private fun setupWeatherSourceUI(view: View) {
        val txtvw_weather_source = view.findViewById<TextView>(R.id.txtvw_weather_source)
        val weatherSourceString =
            StringBuilder(txtvw_weather_source.text).append("Open Weather Maps").toString()
        val spWeatherSourceString = SpannableString(weatherSourceString)
        spWeatherSourceString.setSpan(UnderlineSpan(), 17, weatherSourceString.length, 0)
        spWeatherSourceString.setSpan(
            ForegroundColorSpan(R.color.colorAccent),
            17,
            weatherSourceString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtvw_weather_source.text = spWeatherSourceString
        txtvw_weather_source.setOnClickListener {
            val url = "https://openweathermap.org/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        preferenceViewModel = ViewModelProvider(
            this,
            PreferenceViewModelFactory(
                activity?.appDataStore
            )
        ).get(PreferenceViewModel::class.java)
    }
}