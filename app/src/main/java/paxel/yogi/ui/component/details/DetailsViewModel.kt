package paxel.yogi.ui.component.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import paxel.yogi.data.DataRepositorySource
import paxel.yogi.data.dto.employee.Employee
import paxel.yogi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
open class DetailsViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val employeePrivate = MutableLiveData<Employee>()
    val employee: LiveData<Employee> get() = employeePrivate

    fun initIntentData(employee: Employee) {
        employeePrivate.value = employee
    }

}
