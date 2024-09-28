package com.carly.features.addcar.domain

import com.carly.features.addcar.rp.AddCarRepository
import com.carly.features.addcar.vm.SelectionItem
import com.carly.features.addcar.vm.Step
import kotlinx.coroutines.flow.Flow

class FetchStepDataUseCaseUseCase(private val repository: AddCarRepository) {

    operator fun invoke(
        params: Params,
    ): Flow<List<SelectionItem>> {

        return when (params.currentStep) {
            Step.SelectBrand -> repository.getBrands(
            )

            Step.SelectBuildYear -> repository.getBuildYears(
                seriesId = params.series?.id
                    ?: throw IllegalArgumentException("Series id is null")
            )

            Step.SelectFuelType -> repository.getFuelTypes()
            Step.SelectSeries -> repository.getSeries(
                brandId = params.brand?.id
                    ?: throw IllegalArgumentException("Brand id is null"),
            )

        }

    }


    data class Params(
        val currentStep: Step,
        val brand: SelectionItem?,
        val series: SelectionItem?,
        val year: SelectionItem?,
    ) {
        companion object {
            fun create(
                currentStep: Step,
                brand: SelectionItem? = null,
                series: SelectionItem? = null,
                year: SelectionItem? = null
            ): Params {
                return Params(
                    currentStep = currentStep,
                    brand = brand,
                    series = series,
                    year = year
                )
            }
        }
    }
}