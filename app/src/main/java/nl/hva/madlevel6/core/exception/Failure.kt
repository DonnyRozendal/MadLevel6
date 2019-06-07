package nl.hva.madlevel6.core.exception

class Failure {

    class GenericError(message: String) : Throwable(message)
    class ServerError(throwable: Throwable) : Throwable(throwable)
    class NetworkConnection : Throwable()

}