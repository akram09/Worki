package oxxy.kero.roiaculte.team7.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ParameterizedTypeName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.controllers.Controller
import oxxy.kero.roiaculte.team7.processor.controllers.WorkiController
import oxxy.kero.roiaculte.team7.processor.models.Result
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

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

      lateinit var  controller: Controller


    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {

       p1?.getElementsAnnotatedWith(WorkiUsecase::class.java)?.forEach {
           val generateRessourceFolder = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
           if(generateRessourceFolder.isEmpty()){
               processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,"Cannot find path to the generated kotlin files try rebuilding ")
               return@forEach
           }
           controller = Builder.provideControler(it , it.getAnnotation(WorkiUsecase::class.java) ,
               processingEnv.elementUtils.getPackageOf(it).simpleName.toString() , it.simpleName.toString())
           var isValidationError = false
           controller.validate(it) {
               error , msg->
                  processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,msg)
               isValidationError = true
               return@validate
           }
           if(isValidationError){
               return true
           }
           runBlocking {
               controller.generateUseCase()
           }

       }
        return true
    }
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

}