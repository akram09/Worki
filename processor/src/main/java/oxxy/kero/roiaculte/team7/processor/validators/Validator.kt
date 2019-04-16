package oxxy.kero.roiaculte.team7.processor.validators

import javax.lang.model.element.Element

interface Validator {
    fun validate(element:Element , onError:(errorType:ErrorType ,message:String)->Unit):Boolean
}
enum class ErrorType{
    Warning , Error
}