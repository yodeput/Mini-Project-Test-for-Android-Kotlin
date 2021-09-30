package paxel.yogi.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import paxel.yogi.databinding.SplashLayoutBinding
import paxel.yogi.ui.base.BaseActivity
import paxel.yogi.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import paxel.yogi.BuildConfig
import paxel.yogi.ui.component.main.MainActivity


@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: SplashLayoutBinding
    var versionName: String = BuildConfig.VERSION_NAME
    var versionCode: Int = BuildConfig.VERSION_CODE

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvAppVersion.text = "V " + versionName
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, MainActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
