package oxxy.kero.roiaculte.team7.processor

import com.google.auto.service.AutoService
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
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

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
       p1?.getElementsAnnotatedWith(WorkiUsecase::class.java)?.forEach {
           if(!it.verifyIsInterface()){
               processingEnv.messager.printMessage(Diagnostic.Kind.ERROR  ,
                   "Annotation work only with interface please see the ${it.simpleName}" )
               return true
           }

       }
        return true
    }
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
    fun  Element.verifyIsInterface():Boolean {
        return this.kind ==ElementKind.INTERFACE
    }
}