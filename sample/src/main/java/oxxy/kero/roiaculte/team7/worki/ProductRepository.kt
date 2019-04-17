package oxxy.kero.roiaculte.team7.worki

import oxxy.kero.roiaculte.team7.annotation.Either
import oxxy.kero.roiaculte.team7.annotation.Failure
import oxxy.kero.roiaculte.team7.annotation.None

interface ProductRepository {
    fun addProduct(int:UserId):Either<Failure.SubmitionFailure , Result>
    fun deleteProduct(none: None):Either<Failure.SubmitionFailure , None>
}