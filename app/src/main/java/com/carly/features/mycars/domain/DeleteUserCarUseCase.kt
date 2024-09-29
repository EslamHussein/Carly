package com.carly.features.mycars.domain

import com.carly.features.mycars.rp.MyCarsRepository
import com.carly.features.mycars.ui.dto.MyCar

class DeleteUserCarUseCase(private val repository: MyCarsRepository) {
    suspend operator fun invoke(myCar: MyCar): Result<Unit> {
        return repository.deleteCar(myCar)
    }
}