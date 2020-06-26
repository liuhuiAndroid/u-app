package com.study.u.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.study.u.R
import com.study.u.ext.decode
import com.study.u.ext.otherwise
import com.study.u.ext.yes
import com.study.u.utilities.MMKVConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(1000)
            }
            decode(MMKVConstants.TOKEN, "").isNotEmpty().yes {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }.otherwise {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }
    }

}