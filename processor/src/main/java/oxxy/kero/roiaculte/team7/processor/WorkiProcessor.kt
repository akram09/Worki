package oxxy.kero.roiaculte.team7.processor

import com.google.auto.service.AutoService
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.controllers.WorkiController
import oxxy.kero.roiaculte.team7.processor.models.Result
import javax.annotation.processing.*
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
    private lateinit var  controller: WorkiController

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
       p1?.getElementsAnnotatedWith(WorkiUsecase::class.java)?.forEach {
            controller = WorkiController()
            val resultIsInterface = controller.isInstanceInterface(it)
           if(resultIsInterface is Result.Failure){
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR , resultIsInterface.message)
               return true
           }
           val resultOfIsPublic  = controller.isPublicClass(it)
           if(resultOfIsPublic  is Result.Failure){
               processingEnv.messager.printMessage(Diagnostic.Kind.ERROR , resultOfIsPublic.message)
               return true
           }
           val resultIsImplementingEither = controller.isImplementingOnlyEither(it)
           if(resultIsImplementingEither is Result.Failure){
               processingEnv.messager.printMessage(Diagnostic.Kind.ERROR , resultIsImplementingEither.message)
               return true
           }
           val generateRessourceFolder = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
           if(generateRessourceFolder.isEmpty()){
               processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,"Cannot find path to the generated kotlin files try rebuilding ")
               return@forEach
           }
           controller.init(it ,processingEnv.elementUtils.getPackageOf(it).simpleName.toString() , generateRessourceFolder )


       }
        return true
    }
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

}