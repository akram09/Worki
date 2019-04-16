package oxxy.kero.roiaculte.team7.processor.generators

import com.squareup.kotlinpoet.*
import dagger.Binds
import dagger.Module
import oxxy.kero.roiaculte.team7.processor.models.UseCaseProviderModule
import oxxy.kero.roiaculte.team7.processor.models.UsecaseModel
import java.io.File

class ModuleGenerator(private val usecaseModel: UseCaseProviderModule
                      , private  val packageName:String, private val  root:String):Generator {

//    fun generate(){
//        val file  = File(root , "")
//        val fileSpec = FileSpec.builder(packageName , "WorkiModule")
//            .addType(generateClass())
//            .build()
//        fileSpec.writeTo(file)
//    }

    fun generateClass():TypeSpec{
        return TypeSpec.classBuilder("WorkiModule")
            .addModifiers(KModifier.ABSTRACT)
            .addAnnotation(Module::class.java)
            .addFunction(generateProvideFunction())
            .build()

    }
    fun generateProvideFunction():FunSpec{
        return FunSpec.builder("provide${usecaseModel.generatedClassName}")
            .addModifiers(KModifier.ABSTRACT)
            .returns(usecaseModel.usecaseClass)
            .addAnnotation(Binds::class.java)
//            .addParameter("usecase", )
            .build()
    }

}