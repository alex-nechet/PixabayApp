package com.alexnechet.pixabay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.alexnechet.feature.images.list.ImageListFragmentArgs
import com.alexnechet.feature.images.list.ImageListFragmentDirections
import com.alexnechet.pixabay.R

class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val directions = ImageListFragmentDirections
                .imagesListWithParams(
                    getString(com.alexnechet.feature.images.R.string.default_search_word)
                )
            navController.navigate(directions)
        }
    }
}