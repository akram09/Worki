package oxxy.kero.roiaculte.team7.processor.Uils

import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror

fun Element.getImplementedInterfaces()= (this as TypeElement).interfaces
fun TypeMirror.getTypeArg()= (this as DeclaredType).typeArguments