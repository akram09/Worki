package oxxy.kero.roiaculte.team7.processor

import kotlin.Exception

class FunctionIsNotPublicException(fonctionName:String , repositoryName:String):Exception(){
    override val message = "The function $fonctionName located in $repositoryName is not public"
}
class RepositoryNotInterface(repositoryName:String):Exception(){
  override  val message = "The $repositoryName must be an interface "
}
class FonctionReturnException(fonctionName: String , returnClass:String):Exception(){
    override val message ="The function $fonctionName must return Either and not $returnClass "
}
class FonctionReturnClassException(fonctionName: String):Exception(){
    override val message ="The fonction $fonctionName must return a class "
}
class FonctioParametreSizeException(fonctionName: String , usecaseName:String):Exception(){
    override val message = "The function $fonctionName must have only one parametre which is the in parametre of $usecaseName"
}