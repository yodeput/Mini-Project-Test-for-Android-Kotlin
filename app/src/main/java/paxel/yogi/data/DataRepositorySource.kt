package paxel.yogi.data

import paxel.yogi.data.dto.employee.EmployeeResponse
import kotlinx.coroutines.flow.Flow
import paxel.yogi.data.dto.employee.EmployeeList


interface DataRepositorySource {
    suspend fun requestEmployee(): Flow<Resource<EmployeeList>>
}
