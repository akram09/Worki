package oxxy.kero.roiaculte.team7.processor.models

sealed class Result<out T> {
     class Success<T>(val t:T):Result<T>()
    class Failure(val message:String ) : Result<Nothing>()
}