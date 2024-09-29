package com.carly.features.mycars.domain

import com.carly.features.mycars.rp.MyCarsRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCarIdUseCase(private val repository: MyCarsRepository) {

    operator fun invoke(): Flow<Long?> = repository.getSelectedCarId()

}