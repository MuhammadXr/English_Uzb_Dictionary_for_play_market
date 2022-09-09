package uz.gita.english_uzbdictionary_for_play_market.ui

import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.english_uzbdictionary_for_play_market.ui.dialog.WordInfoDialog
import uz.gita.english_uzbdictionary_for_play_market.R
import uz.gita.english_uzbdictionary_for_play_market.presenter.MainEngViewModel
import uz.gita.english_uzbdictionary_for_play_market.ui.adapder.WordAdapter
import uz.gita.english_uzbdictionary_for_play_market.utils.getWordDataList

class MainEng : Fragment(R.layout.fragment_main) {

    lateinit var list : RecyclerView
    lateinit var adapter : WordAdapter
    lateinit var searchView: SearchView
    lateinit var handler: Handler
    lateinit var btnFav: FloatingActionButton
    lateinit var prgs: ProgressBar
    val viewModel : MainEngViewModel by viewModels ()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = Handler(Looper.getMainLooper())

        viewModel.showWordInfo.observe(this) {
            val dialog = WordInfoDialog()
            dialog.setFavouriteClickListener {data ->
                it.isFavourite = if(it.isFavourite == 0) 1 else 0
                viewModel.changeFavorite(it)
            }
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            dialog.arguments = bundle
            dialog.show(parentFragmentManager, "Info")
        }

        viewModel.gotoFav.observe(this){
            findNavController().navigate(R.id.action_main_to_favorites)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            val btn = findViewById<ImageView>(R.id.btn_back_menu)
            btn.setOnClickListener {
                findNavController().navigateUp()
            }

            prgs = findViewById(R.id.progress_bar)
        }
        btnFav = view.findViewById(R.id.btn_favorites)
        searchView = view.findViewById(R.id.search_view)
        adapter = WordAdapter()
        list = view.findViewById(R.id.list_word)
        list.adapter = adapter

        viewModel.cursorOfWords.observe(viewLifecycleOwner){
            if(it == null || it.count == 0){
                prgs.visibility = ProgressBar.VISIBLE
            }
            else
                prgs.visibility = ProgressBar.GONE

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

        btnFav.setOnClickListener {
            viewModel.gotoFav()
        }

        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 ) btnFav.hide()
                else btnFav.show()
            }
        })
    }
}