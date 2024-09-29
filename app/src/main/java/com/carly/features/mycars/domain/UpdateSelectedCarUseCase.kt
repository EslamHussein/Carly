package com.carly.features.mycars.domain

import com.carly.features.mycars.rp.MyCarsRepository

class UpdateSelectedCarUseCase(private val repository: MyCarsRepository) {
    suspend operator fun invoke(carId: Long): Result<Unit> {
        return repository.setSelectedCarId(carId)
    }
}