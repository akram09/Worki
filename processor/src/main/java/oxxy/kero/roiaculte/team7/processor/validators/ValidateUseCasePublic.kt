package oxxy.kero.roiaculte.team7.processor.validators

import com.squareup.kotlinpoet.asTypeName
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

class ValidateUseCasePublic:Validator {
    override fun validate(element: Element, onError: (errorType: ErrorType, message: String) -> Unit): Boolean {
        return if(element.modifiers.containsAll(listOf(Modifier.PRIVATE , Modifier.PROTECTED ))){
            onError(ErrorType.Error , "${element.simpleName} must be public to generate the worki usecase for it")
            false
        }else{
            true
        }
    }
}