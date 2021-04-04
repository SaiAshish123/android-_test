package net.exercise.data.repositories

import retrofit2.Response
import java.io.IOException

/**
 * @author Ashish
 * This class is used to get get the response Object using Suspend Fun
 */
abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            /**
             * Throws Error message
             */
            throw ApiException(response.code().toString())
        }
    }

}

/**
 * This is used to throw the message in string based on Response code .
 */
class ApiException(message: String): IOException(message)