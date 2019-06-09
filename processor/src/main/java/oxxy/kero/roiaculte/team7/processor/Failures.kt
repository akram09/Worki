package oxxy.kero.roiaculte.team7.processor

import java.lang.Exception

class FunctionIsNotPublicException(fonctionName:String , repositoryName:String):Exception(){
    override val message = "The function $fonctionName located in $repositoryName is not public"
}