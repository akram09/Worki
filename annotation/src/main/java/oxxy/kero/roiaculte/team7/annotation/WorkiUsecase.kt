package oxxy.kero.roiaculte.team7.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class WorkiUsecase(val usecaseClass:KClass<*>)