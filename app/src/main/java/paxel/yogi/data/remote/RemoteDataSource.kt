package paxel.yogi.data.remote

import paxel.yogi.data.Resource
import paxel.yogi.data.dto.employee.EmployeeList
import paxel.yogi.data.dto.employee.EmployeeResponse


internal interface RemoteDataSource {
    suspend fun requestEmployee(): Resource<EmployeeList>
}
