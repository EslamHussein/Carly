package com.carly.features.mycars.vm

import com.carly.features.mycars.ui.dto.MyCar

data class MyCarsState(val cars: List<MyCar> = emptyList(), val selectedCarId: Long? = null)
