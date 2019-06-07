package nl.hva.madlevel6.features.data.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("results")
    val results: T
)