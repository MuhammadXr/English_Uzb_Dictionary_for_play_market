package uz.gita.english_uzbdictionary_for_play_market.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.gita.english_uzbdictionary_for_play_market.R
import uz.gita.english_uzbdictionary_for_play_market.presenter.MenuViewModel
import uz.gita.english_uzbdictionary_for_play_market.presenter.SplashViewModel


class Splash : Fragment(R.layout.fragment_splash) {

    val nav: NavController by lazy { findNavController() }

    val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.gotoMenu.observe(this){
            nav.navigate(R.id.action_splash_to_main)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.gotoMenu()
    }
}