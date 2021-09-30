package paxel.yogi.ui.component.details

import android.os.Bundle
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import paxel.yogi.R
import paxel.yogi.data.dto.employee.Employee
import paxel.yogi.databinding.DetailsLayoutBinding
import paxel.yogi.ui.base.BaseActivity
import paxel.yogi.utils.observe
import paxel.yogi.utils.toCurrency


@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: DetailsLayoutBinding

    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initIntentData(intent.getParcelableExtra("data") ?: Employee())
        supportActionBar?.setTitle(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun observeViewModel() {
        observe(viewModel.employee, ::initializeView)
    }

    private fun initializeView(employeeItem: Employee) {
        binding.tvName.text = employeeItem.employee_name
        binding.tvAge.text = employeeItem.employee_age.toString()
        binding.tvSalary.text = employeeItem.employee_salary.toCurrency()
        val img = if(employeeItem.profile_image.isEmpty()) null else employeeItem.profile_image;
        Picasso.get().load(img).placeholder(R.drawable.img_avatar)
            .into(binding.ivAvatar)

    }
}
