package io.realWorld.android

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.realWorld.android.databinding.ActivityMainBinding
import io.realWorld.android.ui.extensions.loadImage
import io.realworld.api.models.entities.User

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var ivDrawer: ImageView
    private lateinit var tvDrawer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val changeLabel = navController.graph.findNode(R.id.nav_feed)

        ivDrawer = navView.getHeaderView(0).findViewById(R.id.ivDrawer)
        tvDrawer = navView.getHeaderView(0).findViewById(R.id.tvDrawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_feed,R.id.nav_login,R.id.nav_profile_update,R.id.nav_create,R.id.nav_fav
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        authViewModel.user.observe(this) { user ->
            updateDrawer(user)
            Toast.makeText(this, "Logged in as ${user?.username}", Toast.LENGTH_SHORT).show()
            changeLabel?.label = "Welcome, ${user?.username}"
            ivDrawer.loadImage(user?.image.toString())
            tvDrawer.text = user?.username
            navController.navigate(R.id.nav_feed)

        }

    }
    private fun updateDrawer(user: User?){
        when(user) {
            is User -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.activity_main_logged_in)
            }
            else -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.activity_main_drawer)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}