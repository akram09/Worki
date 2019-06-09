package oxxy.kero.roiaculte.team7.domain

import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.annotation.base.EitherInteractor
import oxxy.kero.roiaculte.team7.annotation.base.Failure

data class User(val id:String)
data class Result(val int :Int)
interface AddUser :EitherInteractor<User , Result , Failure.ConfirmEmailFaillure>