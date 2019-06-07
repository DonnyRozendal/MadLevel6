package nl.hva.madlevel6.core.platform

import android.content.Context
import nl.hva.madlevel6.core.extension.networkInfo

class NetworkHandler(private val context: Context) {

    val isConnected get() = context.networkInfo?.isConnected

}