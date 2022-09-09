package uz.gita.english_uzbdictionary_for_play_market.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.gita.english_uzbdictionary_for_play_market.R
import uz.gita.english_uzbdictionary_for_play_market.presenter.MenuViewModel

class Menu : Fragment(R.layout.fragment_menu) {

    val nav:NavController by lazy { findNavController() }

    val viewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.gotoEngDic.observe(this){
            if(it){
                nav.navigate(R.id.action_menu_to_mainEng)
            }
            else{
                nav.navigate(R.id.action_menu_to_mainUzb)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            val btnEng = findViewById<TextView>(R.id.btn_toEng_menu)
            val btnUzb = findViewById<TextView>(R.id.btn_toUzb_menu)

            btnUzb.setOnClickListener {
                viewModel.gotoEngDic(false)
            }

            btnEng.setOnClickListener {
                viewModel.gotoEngDic(true)
            }
        }


    }
}