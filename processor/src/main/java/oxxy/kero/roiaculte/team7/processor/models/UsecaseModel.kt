//package oxxy.kero.roiaculte.team7.processor.models
//
//import com.squareup.kotlinpoet.ClassName
//import com.squareup.kotlinpoet.ParameterizedTypeName
//import com.squareup.kotlinpoet.asTypeName
//import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
//import oxxy.kero.roiaculte.team7.processor.Uils.getImplementedInterfaces
//import oxxy.kero.roiaculte.team7.processor.Uils.getTypeArg
//import oxxy.kero.roiaculte.team7.processor.generators.Model
//import javax.lang.model.element.Element
//import kotlin.reflect.KClass
//import com.sun.xml.internal.messaging.saaj.soap.impl.ElementImpl.getQualifiedName
//import com.google.auto.common.MoreTypes.asElement
//import javax.annotation.processing.ProcessingEnvironment
//import javax.lang.model.element.TypeElement
//import javax.lang.model.type.DeclaredType
//import javax.lang.model.type.MirroredTypeException
//
//
//
//data class UsecaseModel(private val annotation:WorkiUsecase , private  val element: Element ,
//                         private val root:String ,
//                        private val packageName: String
//                        ):Model{
//    val superInterface: SuperInterface  = SuperInterface(element.asType().asTypeName() as ClassName)
//
//
//
//    val input :UseCaseInputType =
//        UseCaseInputType(element.getImplementedInterfaces()[0].getTypeArg()[0].asTypeName() as ClassName)
//
//
//
//     val output:UseCaseOutputType =
//         UseCaseOutputType(element.getImplementedInterfaces()[0].getTypeArg()[1].asTypeName() as ClassName)
//
//
//
//    val failureClass:ClassName  =
//
//        element.getImplementedInterfaces()[0].getTypeArg()[2].asTypeName() as ClassName
//   val workiannotation:Annotation = annotation.let {
//       var repoClass :ClassName
//       var functionName:String
//       try {
//        repoClass = annotation.repositoryClass.asTypeName()
//        functionName = annotation.fonctionName
//       } catch (mte: MirroredTypeException) {
//           repoClass = mte.typeMirror.asTypeName() as ClassName
//           functionName = annotation.fonctionName
//       }
//       Annotation(repoClass , functionName)
//   }
//    val packageInfo = PackageInfo(packageName, root , element.simpleName.toString())
//}
//data class PackageInfo(val packageName :String , val root:String ,  val name:String)