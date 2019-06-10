package oxxy.kero.roiaculte.team7.processor

import com.google.auto.service.AutoService

import kotlinx.coroutines.*
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.annotation.base.EitherInteractor
import oxxy.kero.roiaculte.team7.processor.controllers.Controller
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import kotlin.reflect.full.isSubclassOf

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

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
       val annotatedFunctions = p1?.getElementsAnnotatedWith(WorkiUsecase::class.java)
        val generateRessourceFolder = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
        if(generateRessourceFolder.isEmpty()){
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR ,"Cannot find path to the generated kotlin files try rebuilding ")
            return false
        }

        return true
    }
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
//    private fun checkUseCaseRepetition(fonctionsList:List<ExecutableElement>):Boolean{
//        fonctionsList.forEach{
//            if(it.getAnnotation(WorkiUsecase::class.java).usecaseClass.isSubclassOf(EitherInteractor::class)){
//                return false
//            }
//        }
//
//    }

}