package paxel.yogi.utils

import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt


fun Double.toCurrency():String{
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("USD")

    return format.format(this.roundToInt())
}