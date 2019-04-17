package oxxy.kero.roiaculte.team7.processor.controllers

import oxxy.kero.roiaculte.team7.processor.validators.ErrorType
import javax.lang.model.element.Element

interface Controller {
    fun validate(element: Element, onError:(e: ErrorType, msg:String)->Unit)
   suspend fun generateUseCase()
}