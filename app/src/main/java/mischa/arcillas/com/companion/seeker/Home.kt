package mischa.arcillas.com.companion.seeker

import android.app.AlertDialog
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import mischa.arcillas.com.companion.Login
import mischa.arcillas.com.companion.R
import mischa.arcillas.com.companion.adapter.ProblemAdapter
import mischa.arcillas.com.companion.fragment.ChatFragment
import mischa.arcillas.com.companion.fragment.HomeFragment
import mischa.arcillas.com.companion.model.ProblemsData
import okhttp3.*
import java.io.IOException

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var bnv: BottomNavigationView
    var fm: FragmentManager = getFragmentManager()

/*    lateinit var recyclerProb: RecyclerView*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

/*        recyclerProb = findViewById(R.id.recycler_view_custom_problem)
        recyclerProb.layoutManager = LinearLayoutManager(this)*/


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

        txtTalkCircle.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)

           val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recycler_view_custom_problem)
            val mLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

            val url = "http://172.17.2.132:8000/problems/get"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call?, e: IOException?) {

                }
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()
                    println(body)

                    val gson = GsonBuilder().create()

                    val problem = gson.fromJson(body, ProblemsData::class.java)

                    runOnUiThread {
//                        recyclerView.adapter = ProblemAdapter(problem)
                        val mAdapter = ProblemAdapter(problem)
                        recyclerView?.layoutManager = mLayoutManager
                        recyclerView?.adapter = mAdapter
                    }
                }
            })

            dialog.setView(dialogView)
            dialog.setCancelable(true)
            dialog.setPositiveButton("Submit",{ _: DialogInterface, _: Int ->
                Toast.makeText(this, "Add to Queue", Toast.LENGTH_SHORT).show()
            })

            dialog.setNegativeButton("Cancel", {_:DialogInterface, _:Int ->

            })
            val customDialog = dialog.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener({
            })

        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        /*val extra = intent.extras
        val name_user = extra.getString("name")

        txt_name_user.text = name_user*/
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
        menuInflater.inflate(R.menu.home, menu)
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
