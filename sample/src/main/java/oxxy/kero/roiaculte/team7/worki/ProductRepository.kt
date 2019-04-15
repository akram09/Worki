package oxxy.kero.roiaculte.team7.worki

import oxxy.kero.roiaculte.team7.annotation.Either
import oxxy.kero.roiaculte.team7.annotation.Failure

interface ProductRepository {
    fun addproductusecase(int:Integer):Either<Failure.SubmitionFailure , Integer>
}