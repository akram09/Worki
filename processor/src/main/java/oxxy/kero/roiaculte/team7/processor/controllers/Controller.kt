package oxxy.kero.roiaculte.team7.processor.controllers

import javax.lang.model.element.Element

interface Controller {
    suspend fun validate()
   suspend fun generateUseCase()
}