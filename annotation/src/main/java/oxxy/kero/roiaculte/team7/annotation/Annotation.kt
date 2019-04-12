package oxxy.kero.roiaculte.team7.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Usecase(val repositoryClass:KClass<*>)