package oxxy.kero.roiaculte.team7.domain

import oxxy.kero.roiaculte.team7.annotation.base.Either
import oxxy.kero.roiaculte.team7.annotation.base.Failure

interface UserRepository {
    fun addUser(user:User):Either<Failure.ConfirmEmailFaillure , Result>
}