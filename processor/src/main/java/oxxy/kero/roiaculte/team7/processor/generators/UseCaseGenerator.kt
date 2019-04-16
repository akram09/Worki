package oxxy.kero.roiaculte.team7.processor.generators

import oxxy.kero.roiaculte.team7.annotation.CouroutineDispatchers
import com.squareup.kotlinpoet.*
import kotlinx.coroutines.CoroutineDispatcher
import oxxy.kero.roiaculte.team7.annotation.Either
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import java.io.File
import javax.inject.Inject

class UseCaseGenerator(private val usecaseModel: UsecaseModel  ,private  val packageName:String  , private val  root:String ){
    fun generate(){
        val usecaseGeneratedFile = File(root , "")
            val spec = FileSpec.builder(packageName ,"Generated_${usecaseModel.name}" )
                .addType(generateClass()).build()
        spec.writeTo(usecaseGeneratedFile)
    }
    private fun generateClass(): TypeSpec {

        return TypeSpec.classBuilder("Generated_${usecaseModel.name}")
            .addSuperinterface(usecaseModel.superInterface.interfaceClass)
            .primaryConstructor(
                FunSpec.constructorBuilder().addParameter("couroutineDispatchers"
            , CouroutineDispatchers::class  ).addParameter("repo", usecaseModel.repositoryClass)
                    .addAnnotation(Inject::class)
                    .build()
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
           .returns(Either::class.asClassName().parameterizedBy(usecaseModel.failureClass , usecaseModel.output.inputClass))
           .addCode("return repo.${usecaseModel.name.toLowerCase()}(executeParams)")
            .addModifiers(KModifier.OVERRIDE).addModifiers(KModifier.SUSPEND).build()
    }
    private fun generateIoPropertyOverriding():PropertySpec{
        return PropertySpec.builder("dispatcher", CoroutineDispatcher::class
            , KModifier.OVERRIDE  ).initializer("couroutineDispatchers.computaion")
            .build()

    }
    private fun generateMainPropertyOverriding():PropertySpec{
        return PropertySpec.builder("ResultDispatcher", CoroutineDispatcher::class , KModifier.OVERRIDE)
            .initializer("couroutineDispatchers.main")
            .build()
    }
    private fun generateRepoProperty():PropertySpec{
        return  PropertySpec.builder("repo", usecaseModel.repositoryClass)
            .initializer("repo").build()
    }

}

