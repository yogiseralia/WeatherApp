package io.github.yogiseralia.weatherapp.dagger

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext