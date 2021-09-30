package paxel.yogi.data.local

import android.content.Context
import android.content.SharedPreferences
import paxel.yogi.data.Resource
import javax.inject.Inject


class LocalData @Inject constructor(val context: Context) {

    fun setPref(ids: Set<String>): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences("pref_data", 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet("test", ids)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }
}

