    package oxxy.kero.roiaculte.team7.processor.validators

import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.FonctioParametreSizeException
import oxxy.kero.roiaculte.team7.processor.Uils.getUseCaseProperty
import javax.lang.model.element.ExecutableElement

class ValidateParametreType:Validator {
    override suspend fun validate(fonctionElement: ExecutableElement , workiUsecase: WorkiUsecase) {
        if(fonctionElement.parameters.size!=1){
            throw FonctioParametreSizeException(fonctionElement.simpleName.toString() , getUseCaseName(workiUsecase).toString())
        }

    }
    private fun getUseCaseName(workiUsecase: WorkiUsecase):String?{
       return  workiUsecase.getUseCaseProperty({
            it.simpleName
        }, {
            it.simpleName.toString()
        })
    }

}