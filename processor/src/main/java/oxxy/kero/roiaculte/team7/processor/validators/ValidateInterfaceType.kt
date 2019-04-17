package oxxy.kero.roiaculte.team7.processor.validators

import com.squareup.kotlinpoet.asTypeName
import javax.inject.Inject
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

class ValidateInterfaceType @Inject constructor(): Validator {
    override fun validate(element: Element, onError: (errorType: ErrorType, message: String) -> Unit):Boolean {
        val elementKind = (element as TypeElement).kind
        return if(elementKind!= ElementKind.INTERFACE){
              onError(ErrorType.Error , "${element.simpleName} must be an interface ")
            false
          }else true
    }
}