package io.github.yogiseralia.weatherapp.data.networking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.yogiseralia.weatherapp.currentweather.Latlng
import io.github.yogiseralia.weatherapp.data.db.WeatherDao
import io.github.yogiseralia.weatherapp.mock
import io.github.yogiseralia.weatherapp.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest : TestCase() {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var weatherRepository: WeatherRepository

    @Mock
    private lateinit var apiServiceMock: OpenWeatherAPIService

    @Mock
    private lateinit var weatherDaoMock: WeatherDao

    @Mock
    private lateinit var mapperMock: WeatherAPIToDBMapper

//    private val latLng = Latlng(anyDouble(), anyDouble())


    @Before
    public override fun setUp() {
        super.setUp()
        val apiServiceMock: OpenWeatherAPIService = mock<OpenWeatherAPIService>()
        val weatherDaoMock: WeatherDao = mock<WeatherDao>()
        val mapperMock: WeatherAPIToDBMapper = mock<WeatherAPIToDBMapper>()

        weatherRepository = WeatherRepository(
            apiServiceMock,
            weatherDaoMock,
            mapperMock
        )
    }

    @After
    public override fun tearDown() {
    }

}