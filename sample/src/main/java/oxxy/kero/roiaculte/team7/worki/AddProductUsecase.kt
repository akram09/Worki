package oxxy.kero.roiaculte.team7.worki

import oxxy.kero.roiaculte.team7.annotation.Failure
import oxxy.kero.roiaculte.team7.annotation.EitherInteractor
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase

@WorkiUsecase(ProductRepository::class)
interface AddProductUsecase: EitherInteractor<UserId , Result, Failure.SubmitionFailure>

data class  UserId(val id:String )
data class Result(val result :Int)


