package oxxy.kero.roiaculte.team7.processor

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

abstract  class UseCaseModel(val repositoryClass:KClass<*>, val useCaseClass: KClass<*>,
                        val usecaseInputClass:KClass<*>
                        , val usecaseOutpoutClass:KClass<*>
                        , val repositoryFunction:KFunction<*>?= null){
     abstract val repositoryFunctionName :String
}

class SuspendingEitherUsecase constructor( val failureClass:KClass<*>,
                                             repositoryClass:KClass<*>,  useCaseClass: KClass<*>,
                                             usecaseInputClass:KClass<*>
                                            ,  usecaseOutpoutClass:KClass<*>
                                            ,  repositoryFunction:KFunction<*>?= null):UseCaseModel(
     repositoryClass, useCaseClass, usecaseInputClass, usecaseOutpoutClass, repositoryFunction
 ){
    override val repositoryFunctionName: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}
