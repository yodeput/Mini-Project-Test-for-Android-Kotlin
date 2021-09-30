package paxel.yogi.data.dto.employee

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

data class EmployeeList(val employeeList: List<Employee>?)

@JsonClass(generateAdapter = false)
@Parcelize
data class EmployeeResponse(

    @Json(name="data")
    val data: List<Employee>? = null,

    @Json(name="message")
    val message: String? = null,

    @Json(name="status")
    val status: String? = null
) : Parcelable