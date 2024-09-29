package com.carly.features.mycars.domain

import com.carly.features.mycars.rp.MyCarsRepository

class GetMyCarsUseCase(private val myCarsRepository: MyCarsRepository) {
    operator fun invoke() = myCarsRepository.getMyCars()
}