package oxxy.kero.roiaculte.team7.processor.validators

import com.squareup.kotlinpoet.asTypeName
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.FunctionIsNotPublicException
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier

class ValidateFunctionModifiers:Validator {
    override suspend fun validate(element: ExecutableElement , workiUsecase: WorkiUsecase)  {
        val executableElement = element
        executableElement.modifiers.apply {
            if (contains(Modifier.PRIVATE) or contains(Modifier.PROTECTED) ){
                throw FunctionIsNotPublicException(executableElement.simpleName.toString() , executableElement.enclosingElement.simpleName.toString())
            }

        }
    }
}