package oxxy.kero.roiaculte.team7.processor

import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.processor.controllers.Controller
import oxxy.kero.roiaculte.team7.processor.controllers.WorkiController
import oxxy.kero.roiaculte.team7.processor.generators.Generator
import oxxy.kero.roiaculte.team7.processor.generators.Model
import oxxy.kero.roiaculte.team7.processor.generators.UseCaseGenerator
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import oxxy.kero.roiaculte.team7.processor.validators.ValidateInterfaceType
import oxxy.kero.roiaculte.team7.processor.validators.ValidateUseCasePublic
import oxxy.kero.roiaculte.team7.processor.validators.Validator
import javax.lang.model.element.Element

object Builder {
    fun provideControler(element: Element , annotation: WorkiUsecase , packageName:String , root:String): Controller {
        return WorkiController(provideGenerators(element, annotation , packageName ,root) , provideValidator())
    }

    private fun provideValidator():List<Validator>{
        return listOf(ValidateInterfaceType(), ValidateInterfaceType(), ValidateUseCasePublic())
    }
    private fun provideGenerators(element :Element, annotation:WorkiUsecase , packageName: String , root: String
    ):Pair<UseCaseGenerator, UsecaseModel>{
        return Pair(UseCaseGenerator() , UsecaseModel(annotation, element ,packageName , root ))
    }
}