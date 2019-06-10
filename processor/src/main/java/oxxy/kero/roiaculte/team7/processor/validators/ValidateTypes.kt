package oxxy.kero.roiaculte.team7.processor.validators

import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.annotation.base.Either
import oxxy.kero.roiaculte.team7.processor.FonctionReturnException
import oxxy.kero.roiaculte.team7.processor.Uils.isEither
import java.lang.annotation.ElementType
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType

class ValidateTypes:Validator {
    override suspend fun validate(fonctionElement: ExecutableElement , workiUsecase: WorkiUsecase) {
        val returnType = fonctionElement.returnType
        if(!returnType.isEither(fonctionElement.simpleName.toString())){
            throw FonctionReturnException(fonctionElement.simpleName.toString() , (returnType as TypeElement).simpleName.toString())
        }
    }

}