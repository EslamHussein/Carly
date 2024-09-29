package com.carly.features.addcar.domain

import com.carly.features.addcar.rp.AddCarRepository
import com.carly.features.addcar.vm.UserCar

/**
 * Use case for adding a new car
 */
class AddNewCarUseCase(private val repository: AddCarRepository) {
    suspend operator fun invoke(userCar: UserCar): Result<Long> {
        return repository.addUserCar(userCar)
    }
}