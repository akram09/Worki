package oxxy.kero.roiaculte.team7.processor.controllers


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import oxxy.kero.roiaculte.team7.processor.generators.Generator
import oxxy.kero.roiaculte.team7.processor.generators.Model
import oxxy.kero.roiaculte.team7.processor.generators.UseCaseGenerator
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import oxxy.kero.roiaculte.team7.processor.validators.ErrorType

import oxxy.kero.roiaculte.team7.processor.validators.Validator
import javax.lang.model.element.Element


class WorkiController  constructor(val generators:Pair<UseCaseGenerator , UsecaseModel> , val validators:List<Validator>):Controller {

    override fun validate(element: Element , onError:(e:ErrorType , msg:String)->Unit){
        validators.forEach {
            val isValidated = it.validate(element , onError)
            if(!isValidated){
                return@forEach
            }
        }
    }
    override suspend fun generateUseCase(){
        generators.first.generate(generators.second)
    }

}
