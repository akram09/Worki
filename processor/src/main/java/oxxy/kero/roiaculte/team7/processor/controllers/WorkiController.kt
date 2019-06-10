package oxxy.kero.roiaculte.team7.processor.controllers

import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import javax.lang.model.element.ExecutableElement


class WorkiController  constructor(private val functionAnnotated: ExecutableElement , private val usecase: WorkiUsecase):Controller {
    override suspend fun validate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun generateUseCase() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //    private val validators:List<Validator> by lazy {
//             Builder.provideValidator()
//    }
//
////    private val generator:UseCaseGenerator by lazy {
////             Builder.provideGenerators()
////    }
//
////    override suspend fun validate(element: Element ){
////        validators.forEach {
////            val isValidated = it.validate(element , onError)
////            if(!isValidated){
////                return@forEach
////            }
////        }
////    }
////    override suspend fun generateUseCase(){
////        generator.first.generate(generator.second)
////    }
//
}
