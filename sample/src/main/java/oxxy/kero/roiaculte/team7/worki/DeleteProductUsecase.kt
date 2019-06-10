package oxxy.kero.roiaculte.team7.worki


import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase
import oxxy.kero.roiaculte.team7.annotation.base.EitherInteractor
import oxxy.kero.roiaculte.team7.annotation.base.Failure
import oxxy.kero.roiaculte.team7.annotation.base.None


 interface DelProductUsecase : EitherInteractor<None, None, Failure.SubmitionFailure>