package paxel.yogi.ui.component.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import paxel.yogi.R
import paxel.yogi.data.dto.employee.Employee
import paxel.yogi.databinding.EmployeeItemBinding
import paxel.yogi.ui.base.listeners.RecyclerItemListener
import paxel.yogi.utils.toCurrency


class EmployeeViewHolder(private val itemBinding: EmployeeItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(employeeItem: Employee, recyclerItemListener: RecyclerItemListener<Employee>) {
        itemBinding.tvName.text = employeeItem.employee_name
        itemBinding.lAge.text = "Age"
        itemBinding.lSalary.text = "Salary"
        itemBinding.tvAge.text = employeeItem.employee_age.toString()
        itemBinding.tvSalary.text = employeeItem.employee_salary.toCurrency()
        val img = if(employeeItem.profile_image.isEmpty()) null else employeeItem.profile_image;
        Picasso.get().load(img).placeholder(R.drawable.img_avatar)
            .error(R.drawable.img_avatar).into(itemBinding.ivAvatar)
        itemBinding.cvEmployeeItem.setOnClickListener {
            recyclerItemListener.onItemSelected(
                employeeItem
            )
        }
    }
}

