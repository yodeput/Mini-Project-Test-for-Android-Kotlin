package paxel.yogi.usecase.errors

import paxel.yogi.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
