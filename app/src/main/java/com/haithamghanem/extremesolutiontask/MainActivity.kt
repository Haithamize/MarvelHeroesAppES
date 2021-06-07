package com.haithamghanem.extremesolutiontask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.haithamghanem.extremesolutiontask.databinding.ActivityMainBinding
import com.haithamghanem.extremesolutiontask.presentation.viewmodel.HeroCharactersViewModel
import com.haithamghanem.extremesolutiontask.presentation.viewmodel.HeroCharactersViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HeroCharactersViewModelFactory
    lateinit var viewModel: HeroCharactersViewModel
    private lateinit var bindin: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bindin = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_ExtremeSolutionTask)
        setContentView(bindin.root)

        viewModel = ViewModelProvider(this,factory).get(HeroCharactersViewModel::class.java)

    }


    fun onBackPressed(view: View) = onBackPressed()
}