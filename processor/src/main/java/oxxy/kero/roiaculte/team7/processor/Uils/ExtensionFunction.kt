package oxxy.kero.roiaculte.team7.processor.Uils

import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.asTypeName
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.annotation.base.Either
import oxxy.kero.roiaculte.team7.processor.FonctionReturnClassException
import java.lang.ClassCastException
import java.lang.annotation.ElementType
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass

fun Element.getImplementedInterfaces()= (this as TypeElement).interfaces
fun TypeMirror.getTypeArg()= (this as DeclaredType).typeArguments
fun TypeMirror.isEither(fonctionName:String):Boolean{
    val parametrizedName :ParameterizedTypeName
    try {
        parametrizedName = asTypeName() as ParameterizedTypeName
        return parametrizedName.rawType.canonicalName == Either::class.java.canonicalName
    }catch (e:ClassCastException){
        throw FonctionReturnClassException(fonctionName)
    }
}
fun< T> WorkiUsecase.getUseCaseProperty(doIfCompiled :(KClass<*>)-> T ,doIfNot:(TypeElement)->T ):T{
    return try {
        val usecaseClass = usecaseClass
        doIfCompiled(usecaseClass)
    }catch(e:MirroredTypeException){
        val classTypeMirror = e.typeMirror as DeclaredType
        val classTypeElement = classTypeMirror.asElement() as TypeElement
        doIfNot(classTypeElement)
    }
}