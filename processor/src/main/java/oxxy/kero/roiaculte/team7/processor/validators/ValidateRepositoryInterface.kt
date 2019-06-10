package oxxy.kero.roiaculte.team7.processor.validators

import oxxy.kero.roiaculte.team7.processor.RepositoryNotInterface
import java.lang.ClassCastException
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement

class ValidateRepositoryInterface:Validator {
    override suspend fun validate(fonctionElement: ExecutableElement) {
        val typeElement :TypeElement
        try{
            typeElement = fonctionElement.enclosingElement as TypeElement
        }catch (e:ClassCastException){
            throw RepositoryNotInterface(fonctionElement.enclosingElement.simpleName.toString())
        }
        if(!typeElement.kind.isInterface){
            throw RepositoryNotInterface(fonctionElement.enclosingElement.simpleName.toString())
        }

    }
}