package oxxy.kero.roiaculte.team7.annotation

import kotlinx.coroutines.CoroutineDispatcher

interface CouroutineDispatchers {
    val io:CoroutineDispatcher
    val main:CoroutineDispatcher
    val computaion :CoroutineDispatcher
}