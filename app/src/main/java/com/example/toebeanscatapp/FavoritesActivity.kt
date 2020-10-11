package com.example.toebeanscatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toebeanscatapp.databinding.ActivityFavoritesBinding
import com.example.toebeanscatapp.dependencyInjector.DependencyInjectorImpl
import com.example.toebeanscatapp.helpers.createCustomPicassoLoader
import com.example.toebeanscatapp.presenter.FavoriteContract
import com.example.toebeanscatapp.presenter.FavoritePresenter
import com.example.toebeanscatapp.roomdb.Cats
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FavoriteContract.View {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var picasso: Picasso
    private lateinit var presenter: FavoritePresenter

    private lateinit var catList : List<Cats>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        picasso = createCustomPicassoLoader(this)

        presenter = FavoritePresenter(DependencyInjectorImpl.catRepository(this))
        presenter.onViewReady(this)
        //catRepository = CatRepositoryImpl(CatRoomDatabase.getDatabase(this).catDao())
        setUpToolBar()

        //val catList = presenter.getCatList() // presenter
        presenter.getCatList()

        recycler_view.adapter = CatAdapter(catList, picasso)
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.setHasFixedSize(true)

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



    override fun onNavigationItemSelected(item: MenuItem): Boolean { //
        when (item.itemId) {
            R.id.nav_home -> {
                presenter.onHomeClicked()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun navigateToHome() {
        val intent = Intent(this@FavoritesActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun showCatList(catList: List<Cats>) {
        this.catList = catList
    }
}
