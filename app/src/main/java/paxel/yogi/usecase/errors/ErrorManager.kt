package paxel.yogi.usecase.errors

import paxel.yogi.data.error.Error
import paxel.yogi.data.error.mapper.ErrorMapper
import javax.inject.Inject


class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
