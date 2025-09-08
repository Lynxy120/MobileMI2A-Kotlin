package arzhie.zhafran.mobilemi2akotlin.list.recycler.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R

class BeritaAdapter(
    private val listBerita: List<BeritaModel>,
    val onClickListener: OnAdapterListener
) : RecyclerView.Adapter<BeritaAdapter.ViewBerita>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBerita {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_linear, parent, false)
        return ViewBerita(view)
    }

    override fun onBindViewHolder(holder: ViewBerita, position: Int) {
        val berita = listBerita[position]
        holder.imgBerita.setImageResource(berita.gambar)
        holder.tvJudul.text = berita.judul
        holder.tvTanggal.text = berita.tanggal

        //ketika item kita pilih/click
        holder.itemView.setOnClickListener {
            onClickListener.onClick(berita)
        }
    }

    override fun getItemCount() = listBerita.size

    class ViewBerita(view: View) : RecyclerView.ViewHolder(view){
        val imgBerita = view.findViewById<ImageView>(R.id.imgBerita)
        val tvJudul = view.findViewById<TextView>(R.id.tvJudul)
        val tvTanggal = view.findViewById<TextView>(R.id.tvTanggal)
    }
    interface OnAdapterListener{
        fun onClick(result: BeritaModel)
    }

}