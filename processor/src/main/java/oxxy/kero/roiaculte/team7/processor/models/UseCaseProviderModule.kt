package oxxy.kero.roiaculte.team7.processor.models

import com.squareup.kotlinpoet.ClassName

data class UseCaseProviderModule(
    val usecaseClass:ClassName
    , val generatedClassName:String
)