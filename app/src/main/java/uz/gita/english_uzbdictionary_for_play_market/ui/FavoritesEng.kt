package uz.gita.english_uzbdictionary_for_play_market.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import uz.gita.english_uzbdictionary_for_play_market.ui.dialog.WordInfoDialog
import uz.gita.english_uzbdictionary_for_play_market.R
import uz.gita.english_uzbdictionary_for_play_market.presenter.FavEngViewModel
import uz.gita.english_uzbdictionary_for_play_market.presenter.MainEngViewModel
import uz.gita.english_uzbdictionary_for_play_market.ui.adapder.WordAdapter
import uz.gita.english_uzbdictionary_for_play_market.utils.getWordDataList


class FavoritesEng : Fragment(R.layout.fragment_favorites) {
    lateinit var list : RecyclerView
    lateinit var adapter : WordAdapter
    lateinit var searchView: SearchView
    lateinit var handler: Handler

    val viewModel : FavEngViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = Handler(Looper.getMainLooper())
        viewModel.showWordInfo.observe(this) {
            val dialog = WordInfoDialog()
            dialog.setFavouriteClickListener {
                viewModel.changeFavorite(it)
            }
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            dialog.arguments = bundle
            dialog.show(parentFragmentManager, "Info")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            val btn = findViewById<ImageView>(R.id.btn_back_fav)
            btn.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        searchView = view.findViewById(R.id.search_view)
        adapter = WordAdapter()
        list = view.findViewById(R.id.list_word)
        list.adapter = adapter


        viewModel.cursorOfWords.observe(viewLifecycleOwner){
            adapter.submitList(it.getWordDataList())
        }

        adapter.setFavouriteClickListener {
            viewModel.changeFavorite(it)
        }

        adapter.setItemClickListener {
            viewModel.showData(it)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchBy(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                handler.removeCallbacksAndMessages(null)

                handler.postDelayed(Runnable {
                    viewModel.searchBy(newText.toString())
                }, 300)


                return true
            }

        })
    }
}