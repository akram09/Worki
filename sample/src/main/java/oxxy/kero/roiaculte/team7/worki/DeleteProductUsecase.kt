package oxxy.kero.roiaculte.team7.worki

import oxxy.kero.roiaculte.team7.annotation.EitherInteractor
import oxxy.kero.roiaculte.team7.annotation.Failure
import oxxy.kero.roiaculte.team7.annotation.None
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase

@WorkiUsecase(ProductRepository::class)
interface DeleteProductUsecase :EitherInteractor<None , None, Failure.SubmitionFailure>