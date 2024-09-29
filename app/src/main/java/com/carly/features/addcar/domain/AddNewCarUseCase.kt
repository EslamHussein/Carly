package com.carly.features.addcar.domain

import com.carly.features.addcar.rp.AddCarRepository
import com.carly.features.addcar.vm.CreateUserCar

/**
 * Use case for adding a new car
 */
class AddNewCarUseCase(private val repository: AddCarRepository) {
    suspend operator fun invoke(createUserCar: CreateUserCar): Result<Long> {
        return repository.addUserCar(createUserCar)
    }
}