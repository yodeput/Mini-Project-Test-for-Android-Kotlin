package paxel.yogi.data.remote

import android.util.Log
import paxel.yogi.data.Resource
import paxel.yogi.data.dto.employee.EmployeeResponse
import paxel.yogi.data.dto.employee.EmployeeList
import paxel.yogi.data.error.NETWORK_ERROR
import paxel.yogi.data.error.NO_INTERNET_CONNECTION
import paxel.yogi.data.remote.service.EmployeeService
import paxel.yogi.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.math.log


class RemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {
    override suspend fun requestEmployee(): Resource<EmployeeList> {
        val employeeService = serviceGenerator.createService(EmployeeService::class.java)
        return when (val response = processCall(employeeService::getAll)) {
            is EmployeeResponse -> {
                Resource.Success(data = EmployeeList(response.data))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (responseCode == 200) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
