package paxel.yogi.data.dto.employee


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

@JsonClass(generateAdapter = false)
@Parcelize
data class Employee(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "employee_name")
    val employee_name: String = "",
    @Json(name = "employee_age")
    val employee_age: Int = 0,
    @Json(name = "employee_salary")
    val employee_salary: Double = 0.0,
    @Json(name = "profile_image")
    val profile_image: String = "",
) : Parcelable


