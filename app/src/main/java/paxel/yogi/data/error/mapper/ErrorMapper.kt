package paxel.yogi.data.error.mapper

import android.content.Context
import paxel.yogi.R
import paxel.yogi.data.error.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(SERVER_429, getErrorString(R.string.network_429)),
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.network_no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
        ).withDefault { getErrorString(R.string.network_error) }
}
