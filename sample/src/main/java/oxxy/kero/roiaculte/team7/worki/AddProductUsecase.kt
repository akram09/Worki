package oxxy.kero.roiaculte.team7.worki


import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.annotation.base.EitherInteractor
import oxxy.kero.roiaculte.team7.annotation.base.Failure


interface AddProductUsecase: EitherInteractor<UserId, Result, Failure.SubmitionFailure>

data class  UserId(val id:String )
data class Result(val result :Int)


