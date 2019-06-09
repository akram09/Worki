//package oxxy.kero.roiaculte.team7.processor.validators
//
//import com.squareup.kotlinpoet.asTypeName
//import oxxy.kero.roiaculte.team7.processor.FunctionIsNotPublicException
//import javax.lang.model.element.Element
//import javax.lang.model.element.ExecutableElement
//import javax.lang.model.element.Modifier
//
//class ValidateFunctionModifiers:Validator {
//    override suspend fun validate(element: Element)  {
//        val executableElement = element as ExecutableElement
//        executableElement.modifiers.apply {
//            if (contains(Modifier.PRIVATE) or contains(Modifier.PROTECTED) ){
//                throw FunctionIsNotPublicException(executableElement.simpleName.toString() , executableElement.enclosedElements[0].simpleName.toString())
//            }
//
//        }
//    }
//}