package oxxy.kero.roiaculte.team7.worki

import com.roacult.kero.oxxy.domain.exception.Failure
import com.roacult.kero.oxxy.domain.interactors.EitherInteractor
import oxxy.kero.roiaculte.team7.annotation.WorkiUsecase

@WorkiUsecase(ProductRepository::class)
interface AddProductUsecase:EitherInteractor<Nothing , Nothing , Failure.SubmitionFailure> {
}