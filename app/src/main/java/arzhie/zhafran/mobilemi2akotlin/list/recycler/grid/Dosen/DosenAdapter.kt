package arzhie.zhafran.mobilemi2akotlin.list.recycler.grid.Dosen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R

class DosenAdapter(
    private val listDosen: List<DosenModel>,
    val onClickListener: OnAdapterListener
) : RecyclerView.Adapter<DosenAdapter.ViewDosen>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewDosen {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dosen, parent, false)
        return ViewDosen(view)
    }

    override fun onBindViewHolder(holder: ViewDosen, position: Int) {
        val data = listDosen[position]
        holder.imgDosen.setImageResource(data.gambar)
        holder.tvNama.text = data.nama
        holder.tvNidn.text = data.nidn
        holder.tvBidang.text = data.bidang

        holder.itemView.setOnClickListener {
            onClickListener.onClick(data)
        }
    }

    override fun getItemCount() = listDosen.size

    class ViewDosen(view: View) : RecyclerView.ViewHolder(view) {
        val imgDosen = view.findViewById<ImageView>(R.id.imgGambarDosen)
        val tvNama = view.findViewById<TextView>(R.id.tvNamaDosen)
        val tvNidn = view.findViewById<TextView>(R.id.tvNidn)
        val tvBidang = view.findViewById<TextView>(R.id.tvBidang)
    }

    interface OnAdapterListener {
        fun onClick(result: DosenModel)
    }
}