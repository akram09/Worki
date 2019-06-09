package oxxy.kero.roiaculte.team7.processor.controllers


import oxxy.kero.roiaculte.team7.processor.Builder
import oxxy.kero.roiaculte.team7.processor.generators.UseCaseGenerator
import oxxy.kero.roiaculte.team7.processor.validators.Validator
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement


class WorkiController  constructor(private val functionAnnotated:ExecutableElement) {
    private val validators:List<Validator> by lazy {
             Builder.provideValidator()
    }

//    private val generator:UseCaseGenerator by lazy {
//             Builder.provideGenerators()
//    }

//    override suspend fun validate(element: Element ){
//        validators.forEach {
//            val isValidated = it.validate(element , onError)
//            if(!isValidated){
//                return@forEach
//            }
//        }
//    }
//    override suspend fun generateUseCase(){
//        generator.first.generate(generator.second)
//    }

}
