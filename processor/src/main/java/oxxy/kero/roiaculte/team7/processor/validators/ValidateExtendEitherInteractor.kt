//package oxxy.kero.roiaculte.team7.processor.validators
//
//import com.squareup.kotlinpoet.ParameterizedTypeName
//import com.squareup.kotlinpoet.asTypeName
//import oxxy.kero.roiaculte.team7.processor.Uils.getImplementedInterfaces
//import oxxy.kero.roiaculte.team7.annotation.base.EitherInteractor
//import javax.lang.model.element.Element
//
///**
// * a validator class that will validate the interface implemented by the usecase
// */
//class ValidateExtendEitherInteractor :Validator{
//
//    override fun validate(element: Element, onError: (errorType: ErrorType, message: String) -> Unit): Boolean {
//       val interfaceImplemented = element.getImplementedInterfaces()
//        return if(interfaceImplemented.size==1){
//            val eitherInteractorInterface = interfaceImplemented[0].asTypeName()
//           if(eitherInteractorInterface is ParameterizedTypeName ){
//                if(eitherInteractorInterface.rawType.simpleName == EitherInteractor::class.simpleName){
//                    //todo verify the failure type inherite from Failure
//                    true
//                }else{
//                    onError(ErrorType.Error, "the use ${element.simpleName} must implement only the ${EitherInteractor::class.qualifiedName}")
//                    false
//                }
//            }else{
//                onError(ErrorType.Error, "the use ${element.simpleName} must implement only the ${EitherInteractor::class.qualifiedName}")
//                false
//            }
//        }else{
//            onError(ErrorType.Error , "useCase interface must only implement ${EitherInteractor::class.qualifiedName}")
//             false
//        }
//    }
//
//
//
//}