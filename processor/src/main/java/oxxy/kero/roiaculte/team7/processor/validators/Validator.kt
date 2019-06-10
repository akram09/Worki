package oxxy.kero.roiaculte.team7.processor.validators

import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

interface Validator {
    suspend fun validate(fonctionElement:ExecutableElement, workiUsecase: WorkiUsecase)
}