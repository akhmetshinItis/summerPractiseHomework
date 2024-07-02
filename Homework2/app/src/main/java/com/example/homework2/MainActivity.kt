package com.example.homework2

import android.os.Binder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homework2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var controller: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
            controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
                .navController //вообще не понял что тут происходит, если не сложно расскажи пожалуйста

            controller?.let { navController ->
                binding?.bottomNavigation?.setupWithNavController(navController)
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        controller?.navigateUp()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    }
