package com.carly.core.data.json

import android.content.res.AssetManager
import com.carly.core.data.json.models.CarsData
import com.carly.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

private const val INIT_CAR_DATA = "init_data.json"

class CarsJsonDataSourceImpl(
    private val assetManager: AssetManager,
    private val json: Json,
    private val dispatcherProvider: DispatcherProvider
) : CarsJsonDataSource {
    override suspend fun getBrands(): CarsData = withContext(dispatcherProvider.io) {
        assetManager.open(INIT_CAR_DATA).use { inputStream ->
            run {
                val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
                val builder = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                json.decodeFromString<CarsData>(builder.toString())
            }
        }
    }
}
