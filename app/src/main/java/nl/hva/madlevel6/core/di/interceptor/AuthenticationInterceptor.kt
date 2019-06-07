package nl.hva.madlevel6.core.di.interceptor

import nl.hva.madlevel6.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val newUrl = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.TMD_API_KEY).build()
        request = request.newBuilder().url(newUrl).build()
        return chain.proceed(request)
    }

}