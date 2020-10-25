package com.example.toebeanscatapp
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.example.toebeanscatapp.databinding.ActivityHomeBinding
import com.example.toebeanscatapp.dependencyInjector.DependencyInjectorImpl
import com.example.toebeanscatapp.helpers.createCustomPicassoLoader
import com.example.toebeanscatapp.presenter.HomeContract
import com.example.toebeanscatapp.presenter.HomePresenter
import com.example.toebeanscatapp.roomdb.CatRoomDatabase
import com.example.toebeanscatapp.roomdb.Cats
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    HomeContract.View {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var picasso: Picasso
    private lateinit var scope: CoroutineScope

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var url: String
    private lateinit var name: String
    private lateinit var presenter: HomePresenter
    private lateinit var dialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        scope = CoroutineScope(Dispatchers.IO)

        picasso = createCustomPicassoLoader(this)
        presenter = HomePresenter(DependencyInjectorImpl.catRepository(this))
        presenter.onViewReady(this)

        setUpToolBar()
        setUpListener()

    }

    private fun setUpListener() {

        binding.happyBtn.setOnClickListener {

            //presenter.onHappyBtnClicked()
            scope.launch {
                presenter.onHappyBtnClicked()
            }
        }

        binding.yesBtn1.setOnClickListener {
            presenter.onYesBtnClicked()

        }

        binding.yesBtn2.setOnClickListener {
            presenter.onYesBtnClicked()
        }
    }

    override fun openDialog() {

        val view: View = layoutInflater.inflate(R.layout.open_dialog, findViewById<ViewGroup>(R.id.content), false)
        val dialog = MaterialAlertDialogBuilder(this).setView(view).setCancelable(false).create()
        dialog.show()

        view.findViewById<Button>(R.id.yes).setOnClickListener {
            presenter.onDialogYesClicked(url, view.findViewById<EditText>(R.id.catName).text.toString())
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.no).setOnClickListener {
            dialog.dismiss()
        }


    }

    override fun navigateToFavorites() {
        val intent = Intent(this@HomeActivity, FavoritesActivity::class.java)
        startActivity(intent)
    }

    override fun showCatPicture(url: String) {
        picasso.load(url).into((binding.ivCat))
    }

    override fun setUrl(url: String) {
        this.url = url
    }

    override fun showLoadingDialog() {
        val view: View = layoutInflater.inflate(R.layout.loading_dialog, findViewById<ViewGroup>(R.id.content), false)
        dialog = MaterialAlertDialogBuilder(this).setView(view).setCancelable(true).create()
        dialog.show()
    }

    override fun hideLoadingDialog() {
        dialog.dismiss()
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpToolBar() {
        toolbar = binding.appBar
        setSupportActionBar(toolbar)
        drawerLayout = binding.root
        navView = binding.nvHome

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private suspend fun getRandomCatFromAPI() {
        delay(1000) // delaying this


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_favorites -> {
                presenter.onFavoriteClicked()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}