package mischa.arcillas.com.companion.facilitator

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_facilitator_home.*
import kotlinx.android.synthetic.main.app_bar_facilitator_home.*
import mischa.arcillas.com.companion.Login
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.fragment.ChatFragment
import mischa.arcillas.com.companion.fragment.HomeFragment
import mischa.arcillas.com.companion.seeker.Profile

class FacilitatorHome : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var bnv: BottomNavigationView
    var fm: FragmentManager = getFragmentManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilitator_home)
        setSupportActionBar(toolbar)

        //Bottom Navigation Function
        bnv = findViewById(R.id.main_nav) as BottomNavigationView
        bnv.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var selectedFragment: Fragment? = null
                when (item.itemId) {
                    R.id.nav_home ->
                        selectedFragment = HomeFragment.newInstance()

                /*R.id.nav_notif ->
                    selectedFragment = NotifFragment.newInstance()*/

                    R.id.nav_chat ->
                        selectedFragment = ChatFragment.newInstance()
                }
                var ft: FragmentTransaction = fm.beginTransaction()
                ft.replace(R.id.main_frame, selectedFragment)
                ft.commit()
                return true
            }
        })
        var ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.main_frame, HomeFragment.newInstance())
        ft.commit()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.facilitator_home, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_user -> {
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
            }
            R.id.nav_history -> {

            }
            R.id.nav_about -> {

            }
            R.id.nav_log_out -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
