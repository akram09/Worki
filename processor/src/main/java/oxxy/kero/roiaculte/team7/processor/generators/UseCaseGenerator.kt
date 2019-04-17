package oxxy.kero.roiaculte.team7.processor.generators

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.coroutines.*
import oxxy.kero.roiaculte.team7.annotation.base.CouroutineDispatchers
import oxxy.kero.roiaculte.team7.annotation.base.Either
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import java.io.File
import javax.inject.Inject

class UseCaseGenerator  @Inject  constructor():Generator<UsecaseModel>{

    override suspend fun  generate(model: UsecaseModel) {
        coroutineScope {
            val usecaseGeneratedFile = File(model.packageInfo.root, "")

              val generatedMainDispatcherProperty = async {
                  generateMainPropertyOverriding()
              }



            val generatedIoDispatcherProperty  = async {
                generateIoPropertyOverriding()
            }

            val generatedRepositoryProperty = async {
                generateRepoProperty(model.workiannotation.repositoryClass)
            }

            val generatedInvokeFunction = async {
                generateInvokeFunction(model.input.className , model.failureClass , model.workiannotation.repositoryFunctionName , model.output.className)
            }



            val generatedUsecaseClass = async {  generateClass(model.packageInfo.name ,
                model.superInterface.interfaceClass , model.workiannotation.repositoryClass , generatedIoDispatcherProperty.await() ,
                generatedMainDispatcherProperty.await() , generatedRepositoryProperty.await() , generatedInvokeFunction.await())}



            val spec = FileSpec.builder(model.packageInfo.packageName, "Generated_${model.packageInfo.name}")
                .addType(generatedUsecaseClass.await()).build()
            launch { spec.writeTo(usecaseGeneratedFile)}.join()
        }
    }

    private fun generateClass(generatedUsecaseName:String ,useCaseSuperInterface:ClassName , usecaseRepositoryClass:ClassName,
                              dispatcherIoPropertySpec: PropertySpec , dispatcherMainPropertySpec: PropertySpec,
                              repoPropertySpec: PropertySpec , invokeFunctionFunSpec: FunSpec
                              )
            : TypeSpec {

        return TypeSpec.classBuilder("Generated_$generatedUsecaseName")
            .addSuperinterface(useCaseSuperInterface)
            .primaryConstructor(
                FunSpec.constructorBuilder().addParameter("couroutineDispatchers"
            , CouroutineDispatchers::class  ).addParameter("repo", usecaseRepositoryClass)
                    .addAnnotation(Inject::class)
                    .build()
            )
            .addProperty(dispatcherIoPropertySpec)
            .addProperty(dispatcherMainPropertySpec)
            .addProperty(repoPropertySpec)
            .addFunction(invokeFunctionFunSpec)
            .build()
    }

    private fun generateInvokeFunction(usecaseInputClass:ClassName, useCaseFailureClass:ClassName
                                     ,
                                       functionName:String,
                                        usecaseOutputClass:ClassName):FunSpec{
       return  FunSpec.builder("invoke")
            .addParameter("executeParams", usecaseInputClass)
           .returns(Either::class.asClassName().parameterizedBy(useCaseFailureClass , usecaseOutputClass))
           .addCode("return repo.$functionName(executeParams)")
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
    private  fun generateRepoProperty(repositoryClass:ClassName):PropertySpec{
        return  PropertySpec.builder("repo", repositoryClass)
            .initializer("repo").build()
    }

}

