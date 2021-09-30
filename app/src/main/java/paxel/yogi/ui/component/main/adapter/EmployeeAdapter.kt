package paxel.yogi.ui.component.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import paxel.yogi.data.dto.employee.Employee
import paxel.yogi.databinding.EmployeeItemBinding
import paxel.yogi.ui.base.listeners.RecyclerItemListener
import paxel.yogi.ui.component.main.MainViewModel


class EmployeeAdapter(
    private val employeeListViewModel: MainViewModel,
    private val employeeList: List<Employee>
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener<Employee> = object : RecyclerItemListener<Employee> {
        override fun onItemSelected(data: Employee) {
            employeeListViewModel.openDetails(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemBinding = EmployeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
}

