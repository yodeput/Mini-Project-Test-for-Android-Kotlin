package paxel.yogi.data.remote.service

import paxel.yogi.data.dto.employee.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET


interface EmployeeService {
    @GET("employees")
    suspend fun getAll(): Response<EmployeeResponse>
}
