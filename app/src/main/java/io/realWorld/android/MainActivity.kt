package io.realWorld.android

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import io.realWorld.android.databinding.ActivityMainBinding
import io.realWorld.android.ui.extensions.loadImage
import io.realworld.api.models.entities.User

class MainActivity : AppCompatActivity() {

    companion object {
        const val APP_AUTH = "prefs_auth"
        const val KEY_TOKEN = "token"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var ivDrawer: ImageView
    private lateinit var tvDrawer: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(APP_AUTH , Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]


        setContentView(binding.root)


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


        sharedPreferences.getString(KEY_TOKEN,null)?.let { t->
            authViewModel.getCurrentUser(t)
        }
        authViewModel.user.observe(this) { user ->

            updateDrawer(user)
            Toast.makeText(this, "Logged in as ${user?.username}", Toast.LENGTH_SHORT).show()
            user?.let { changeLabel?.label = "Welcome, ${user.username}" } ?: run { changeLabel?.label = "Home" }
            user?.let { ivDrawer.loadImage(user.image.toString()) } ?: run { ivDrawer.loadImage("data:image/png;base64,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")}
            user?. let {tvDrawer.text = user.username } ?: run {tvDrawer.text = "conduit.com" }

            user?.token?.let { t ->
                sharedPreferences.edit {
                    putString(KEY_TOKEN,t)
                }
            }?: run {
                sharedPreferences.edit {
                    remove(KEY_TOKEN)
                }
            }

            navController.navigate(R.id.nav_feed)
        }
//        navView.setNavigationItemSelectedListener { menu:MenuItem ->
//            when(menu.itemId){
//                R.id.logout ->{
//                    authViewModel.logout()
//                    true
//                }
//                else -> {
//                    true
//                }
//            }
//        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                authViewModel.logout()
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}