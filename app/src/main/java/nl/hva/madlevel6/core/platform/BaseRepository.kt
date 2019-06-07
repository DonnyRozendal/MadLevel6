package nl.hva.madlevel6.core.platform

import nl.hva.madlevel6.core.exception.Failure
import nl.hva.madlevel6.features.data.models.BaseResponse
import retrofit2.Call

abstract class BaseRepository(private val networkHandler: NetworkHandler) {

    fun <T, R> requestList(
        call: Call<BaseResponse<List<T>>>,
        transform: (List<T>) -> List<R>,
        default: List<T>
    ): List<R> {
        if (networkHandler.isConnected == false) throw Failure.NetworkConnection()
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> transform((response.body()?.results ?: default))
                false -> throw Failure.GenericError(response.errorBody()?.string() ?: "")
            }
        } catch (exception: Throwable) {
            throw Failure.ServerError(exception)
        }
        
    }

}