package oxxy.kero.roiaculte.team7.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.asTypeName
import com.sun.xml.internal.ws.api.server.Module
import kotlinx.coroutines.*
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.controllers.Controller
import oxxy.kero.roiaculte.team7.processor.controllers.WorkiController
import oxxy.kero.roiaculte.team7.processor.generators.ModuleGenerator
import oxxy.kero.roiaculte.team7.processor.models.Result
import oxxy.kero.roiaculte.team7.processor.models.UseCaseProviderModule
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import javax.annotation.processing.*
import javax.inject.Inject
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
@SupportedOptions(WorkiProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class WorkiProcessor :AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(WorkiUsecase::class.java.canonicalName)
    }
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    /**
     * the controller will handle the generation and validation operation
     */
    lateinit var  controller: Controller


    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
       val annotatedFunctions = p1?.getElementsAnnotatedWith(WorkiUsecase::class.java)
        val generateRessourceFolder = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
        if(generateRessourceFolder.isEmpty()){
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,"Cannot find path to the generated kotlin files try rebuilding ")
            return false
        }

//       p1?.getElementsAnnotatedWith(WorkiUsecase::class.java)?.forEach {
//           val generateRessourceFolder = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
//           if(generateRessourceFolder.isEmpty()){
//               processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,"Cannot find path to the generated kotlin files try rebuilding ")
//               return@forEach
//           }
//           controller = Builder.provideControler(it , it.getAnnotation(WorkiUsecase::class.java) ,
//               "worki.android" , generateRessourceFolder)
//
//
//
//          //a boolean to store if some validation error happened
//           var isValidationError = false
//           //validate the elemnt
//           controller.validate(it) {
//               error , msg->
//               //if there is error we print the error and return from the process function
//                  processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,msg)
//               isValidationError = true
//               return@validate
//           }
//
//           if(isValidationError){
//               return true
//           }
//             //after validating the elemnt we ll then generate the implementation in a background coroutine
//           runBlocking {
//               controller.generateUseCase()
//           }
//           val module  = ModuleGenerator(UseCaseProviderModule(it.asType().asTypeName() as ClassName , it.simpleName.toString()), "worki.android",generateRessourceFolder )
//         module.generate()
//       }
        return true
    }
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

}