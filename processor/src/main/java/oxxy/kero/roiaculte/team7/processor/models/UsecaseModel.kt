package oxxy.kero.roiaculte.team7.processor.models

import com.squareup.kotlinpoet.ClassName

data class UsecaseModel(val superInterface: SuperInterface ,
                        val input :UseCaseInputType,
                        val name:String
                        , val output:UseCaseOutputType,
                        val failureClass:ClassName ,
                        val repositoryClass:ClassName
                        )