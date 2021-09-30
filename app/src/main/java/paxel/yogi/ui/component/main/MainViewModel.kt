package paxel.yogi.ui.component.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import paxel.yogi.data.DataRepositorySource
import paxel.yogi.data.Resource
import paxel.yogi.data.dto.employee.EmployeeResponse
import paxel.yogi.data.dto.employee.Employee
import paxel.yogi.ui.base.BaseViewModel
import paxel.yogi.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import paxel.yogi.data.dto.employee.EmployeeList
import java.util.Locale.ROOT
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val employeesLiveDataPrivate = MutableLiveData<Resource<EmployeeList>>()
    val employeesLiveData: LiveData<Resource<EmployeeList>> get() = employeesLiveDataPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openDetailsPrivate = MutableLiveData<SingleEvent<Employee>>()
    val openDetails: LiveData<SingleEvent<Employee>> get() = openDetailsPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val employeesSearchDataPrivate = MutableLiveData<EmployeeList>()
    val employeesSearchData: LiveData<EmployeeList> get() = employeesSearchDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getData() {
        viewModelScope.launch {
            employeesLiveDataPrivate.value = Resource.Loading()
                dataRepositoryRepository.requestEmployee().collect {
                    employeesLiveDataPrivate.value = it
                }
        }
    }

    fun openDetails(employee: Employee) {
        openDetailsPrivate.value = SingleEvent(employee)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onSearchClick(search: String) {
        employeesLiveDataPrivate.value?.data?.employeeList?.let {
            employeesSearchDataPrivate.value =
                EmployeeList(it.filter { s -> s.employee_name.contains(search) })
        }
        return
    }
}
