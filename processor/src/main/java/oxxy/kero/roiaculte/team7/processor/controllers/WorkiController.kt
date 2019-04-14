package oxxy.kero.roiaculte.team7.processor.controllers

import com.roacult.kero.oxxy.domain.interactors.EitherInteractor
import com.roacult.kero.oxxy.domain.interactors.None
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.asTypeName
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.Uils.getImplementedInterfaces
import oxxy.kero.roiaculte.team7.processor.Uils.getTypeArg
import oxxy.kero.roiaculte.team7.processor.generators.ModuleGenerator
import oxxy.kero.roiaculte.team7.processor.generators.UseCaseGenerator
import oxxy.kero.roiaculte.team7.processor.models.*
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType

//todo add exception handling when converting from element to typeElement
class WorkiController() {
    private lateinit var moduleGenerator: ModuleGenerator
    private lateinit var useCaseGenerator: UseCaseGenerator

     fun isInstanceInterface(element: Element):Result<None>{
         return if(element.kind==ElementKind.INTERFACE){
             Result.Success(None())
         }else{
             Result.Failure("worki annotation can only be applied to interfaces  see ${element.simpleName}")
         }
     }
    fun isImplementingOnlyEither(element: Element):Result<None>{
       return  if((element.getImplementedInterfaces()[0].asTypeName() as ClassName)
                .simpleName == EitherInteractor::class.simpleName && element.getImplementedInterfaces().size==1
        ){
            Result.Success(None())
        }else{
           Result.Failure("the use case must implement the interface ${EitherInteractor::class.simpleName} and only")
       }
    }
    fun isPublicClass(element: Element):Result<None>{
        return if(element.modifiers.contains(Modifier.PUBLIC))
            Result.Success(None())
        else Result.Failure("the usecase interface must be public see ${element.simpleName}")

    }

    fun init(elemntAnnotated:Element , packageName:String , root:String) {
        elemntAnnotated.apply {
            useCaseGenerator = UseCaseGenerator(
                UsecaseModel(
                    SuperInterface(
                        getImplementedInterfaces()[0].asTypeName() as ClassName
                    ),
                    UseCaseInputType(
                        elemntAnnotated.getImplementedInterfaces()[0].
                    )
                    , simpleName.toString()
                    , UseCaseOutputType(
                        (getImplementedInterfaces()[0].asTypeName() as ParameterizedTypeName).typeArguments[1] as ClassName
                    )
                    , (getImplementedInterfaces()[0].asTypeName() as ParameterizedTypeName).typeArguments[2] as ClassName
                    , getAnnotation(WorkiUsecase::class.java).repositoryClass.asTypeName()
                )
                , packageName , root
            )
        }
    }
    fun generate(){
        useCaseGenerator.generate()
    }
    //todo verify only two input for super and no one for child

}