package paxel.yogi.data

import paxel.yogi.data.dto.employee.EmployeeResponse
import paxel.yogi.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import paxel.yogi.data.dto.employee.EmployeeList
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {

    override suspend fun requestEmployee(): Flow<Resource<EmployeeList>> {
        return flow {
            emit(remoteRepository.requestEmployee())
        }.flowOn(ioDispatcher)
    }
}
