package uz.gita.english_uzbdictionary_for_play_market.ui.adapder

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import uz.gita.english_uzbdictionary_for_play_market.R
import uz.gita.english_uzbdictionary_for_play_market.database.model.WordData
import uz.gita.english_uzbdictionary_for_play_market.database.room.WordDao

class WordAdapter : ListAdapter<WordData, WordAdapter.WordItemViewHolder>(Callback) {

    private var itemClickListener: ((WordData) -> Unit)? = null
    private var favouriteClickListener: ((WordData) -> Unit)? = null

    fun setItemClickListener(block: (WordData) -> Unit) {
        itemClickListener = block
    }

    fun setFavouriteClickListener(block: (WordData) -> Unit) {
        favouriteClickListener = block
    }


    inner class WordItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtWord: TextView = view.findViewById(R.id.txtWord)
        private val txtWord2: TextView = view.findViewById(R.id.txtWord2)
        private val btnStar: ImageView = view.findViewById(R.id.btnStar)


        fun bind(position: Int) {

            val data = getItem(position)

            txtWord.text = data.english
            txtWord2.text = data.uzbek
            setIcon(data)

        }

        init {
            view.setOnClickListener {
                val data = getItem(absoluteAdapterPosition)
                itemClickListener?.invoke(data)
            }
            btnStar.setOnClickListener {
                val p = absoluteAdapterPosition
                val data = getItem(p)
                data.isFavourite = if (data.isFavourite == 0) 1 else 0
                setIcon(data)
                favouriteClickListener?.invoke(data)
                notifyItemChanged(p)
            }
        }

        private fun setIcon(data: WordData){
            if (data.isFavourite == 0) {
                btnStar.setImageResource(R.drawable.ic_baseline_star_border_24)
            } else {
                btnStar.setImageResource(R.drawable.ic_baseline_star_24)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
    )

    override fun onBindViewHolder(holder: WordItemViewHolder, position: Int) = holder.bind(position)


    object Callback: DiffUtil.ItemCallback<WordData>() {
        override fun areItemsTheSame(oldItem: WordData, newItem: WordData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordData, newItem: WordData): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.countable == newItem.countable &&
                    oldItem.english == newItem.english &&
                    oldItem.transcript == newItem.transcript &&
                    oldItem.uzbek == newItem.uzbek &&
                    oldItem.isFavourite == newItem.isFavourite &&
                    oldItem.type == newItem.type
        }
    }

}



//private val WordCallBack = object : DiffUtil.ItemCallback<WordData>() {
//    override fun areItemsTheSame(oldItem: WordData, newItem: WordData): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: WordData, newItem: WordData): Boolean {
//        return oldItem.id == newItem.id &&
//                oldItem.countable == newItem.countable &&
//                oldItem.english == newItem.english &&
//                oldItem.transcript == newItem.transcript &&
//                oldItem.uzbek == newItem.uzbek &&
//                oldItem.isFavourite == newItem.isFavourite &&
//                oldItem.type == newItem.type
//    }
//
//}