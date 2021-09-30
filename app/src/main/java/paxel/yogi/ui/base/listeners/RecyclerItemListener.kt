package paxel.yogi.ui.base.listeners

import paxel.yogi.data.dto.employee.Employee


interface RecyclerItemListener<T> {
    fun onItemSelected(data: T)
}
