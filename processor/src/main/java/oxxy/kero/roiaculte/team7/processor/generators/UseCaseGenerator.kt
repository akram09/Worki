package oxxy.kero.roiaculte.team7.processor.generators

import com.roacult.kero.oxxy.domain.exception.Failure
import com.roacult.kero.oxxy.domain.functional.CouroutineDispatchers
import com.roacult.kero.oxxy.domain.functional.Either
import com.roacult.kero.oxxy.domain.interactors.None
import com.squareup.kotlinpoet.*
import com.sun.net.httpserver.Authenticator
import kotlinx.coroutines.CoroutineDispatcher
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import java.io.File
import javax.inject.Inject

class UseCaseGenerator(private val usecaseModel: UsecaseModel  ,private  val packageName:String  , private val  root:String ){
    fun generate(){
        val usecaseGeneratedFile = File(root , "Generated_${usecaseModel.name}")
            val spec =FileSpec.builder(packageName ,"Generated_${usecaseModel.name}" )
                .addType(generateClass()).build()
        spec.writeTo(usecaseGeneratedFile)
    }
    private fun generateClass():TypeSpec{
        return TypeSpec.classBuilder("Generated_${usecaseModel.name}")
            .addSuperinterface(usecaseModel.superInterface.interfaceClass)
            .addAnnotation(Inject::class)
            .primaryConstructor(FunSpec.constructorBuilder().addParameter("couroutineDispatchers"
            , CouroutineDispatchers::class  ).addParameter("repo", usecaseModel.repositoryClass).build()
            )
            .addProperty(generateIoPropertyOverriding())
            .addProperty(generateMainPropertyOverriding())
            .addProperty(generateRepoProperty())
            .addFunction(generateInvokeFunction())
            .build()
    }
    private fun generateInvokeFunction():FunSpec{
       return  FunSpec.builder("invoke")
            .addParameter("executeParams", usecaseModel.input.InputClass)
            .returns((Either::class.asTypeName() as ParameterizedTypeName).apply {
                plusParameter(usecaseModel.input.InputClass)
                plusParameter(usecaseModel.output.inputClass)
                plusParameter(usecaseModel.failureClass)
            }).addCode("return repository.${usecaseModel.name.toLowerCase()}")
            .addModifiers(KModifier.OVERRIDE).addModifiers(KModifier.SUSPEND).build()
    }
    private fun generateIoPropertyOverriding():PropertySpec{
        return PropertySpec.builder("dispatcher", CoroutineDispatcher::class
            , KModifier.OVERRIDE ,KModifier.PRIVATE ).initializer("couroutineDispatchers.computaion")
            .build()

    }
    private fun generateMainPropertyOverriding():PropertySpec{
        return PropertySpec.builder("ResultDispatcher", CoroutineDispatcher::class , KModifier.PRIVATE , KModifier.OVERRIDE)
            .initializer("couroutineDispatchers.main")
            .build()
    }
    private fun generateRepoProperty():PropertySpec{
        return  PropertySpec.builder("repo", usecaseModel.repositoryClass)
            .initializer("repo").build()
    }

}