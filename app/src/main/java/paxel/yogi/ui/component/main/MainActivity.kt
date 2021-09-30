package paxel.yogi.ui.component.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import paxel.yogi.R
import paxel.yogi.data.Resource
import paxel.yogi.data.dto.employee.Employee
import paxel.yogi.ui.base.BaseActivity
import paxel.yogi.ui.component.details.DetailsActivity
import paxel.yogi.ui.component.main.adapter.EmployeeAdapter
import paxel.yogi.utils.*
import dagger.hilt.android.AndroidEntryPoint
import paxel.yogi.data.dto.employee.EmployeeList
import paxel.yogi.data.error.SEARCH_ERROR
import paxel.yogi.databinding.MainActivityBinding


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: MainActivityBinding

    private val employeeListViewModel: MainViewModel by viewModels()
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun initViewBinding() {
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.title_employee)
        supportActionBar?.elevation = 0.0F
        val layoutManager = LinearLayoutManager(this)
        binding.rvEmployeeList.layoutManager = layoutManager
        binding.rvEmployeeList.setHasFixedSize(true)
        employeeListViewModel.getData()
        binding.tilSearch.boxBackgroundColor = resources.getColor(R.color.cardview_light_background)
        binding.tiSearch.doOnTextChanged { text, start, count, after -> handleSearch(text.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> employeeListViewModel.getData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleSearch(query: String) {
        if (query.isNotEmpty()) {
            employeeListViewModel.onSearchClick(query)
        } else {
            employeeListViewModel.employeesLiveData.value?.let { handleEmployeeList(it) };
        }
    }


    private fun bindListData(employee: EmployeeList) {
        if (!(employee.employeeList.isNullOrEmpty())) {
            employeeAdapter = EmployeeAdapter(employeeListViewModel, employee.employeeList)
            binding.rvEmployeeList.adapter = employeeAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<Employee>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("data", it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }


    private fun showDataView(show: Boolean) {
        binding.layoutNoData.visibility = if (show) GONE else VISIBLE
        binding.rvEmployeeList.visibility = if (show) VISIBLE else GONE
        binding.tilSearch.visibility = VISIBLE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.layoutNoData.toGone()
        binding.rvEmployeeList.toGone()
    }

    private fun handleEmployeeList(status: Resource<EmployeeList>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(employee = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { employeeListViewModel.showToastMessage(it) }
            }
        }
    }

    private fun handleEmployeeSearch(dataList: EmployeeList) {
        bindListData(dataList)
    }

    override fun observeViewModel() {
        observe(employeeListViewModel.employeesLiveData, ::handleEmployeeList)
        observe(employeeListViewModel.employeesSearchData, ::handleEmployeeSearch)
        observeEvent(employeeListViewModel.openDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(employeeListViewModel.showSnackBar)
        observeToast(employeeListViewModel.showToast)

    }
}
