package arzhie.zhafran.mobilemi2akotlin.list.recycler.linear.lat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R


class SekolahAdapter(
    private val listSekolah: List<SekolahModel>,
    val onClickListener: OnAdapterListener
) : RecyclerView.Adapter<SekolahAdapter.ViewBerita>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBerita {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_sekolah, parent, false)
        return ViewBerita(view)
    }

    override fun onBindViewHolder(holder: ViewBerita, position: Int) {
        val sekolah = listSekolah[position]
        holder.imgSekolah.setImageResource(sekolah.gambar)
        holder.tvNama.text = sekolah.nama
        holder.tvTahun.text = sekolah.tahun
        holder.tvTelepon.text = sekolah.telepon
        holder.tvAlamat.text = sekolah.alamat

        //ketika item kita pilih/click
        holder.itemView.setOnClickListener {
            onClickListener.onClick(sekolah)
        }
    }

    override fun getItemCount() = listSekolah.size

    class ViewBerita(view: View) : RecyclerView.ViewHolder(view){
        val imgSekolah = view.findViewById<ImageView>(R.id.imgSekolah)
        val tvNama = view.findViewById<TextView>(R.id.tvNama)
        val tvTahun = view.findViewById<TextView>(R.id.tvTahun)
        val tvTelepon = view.findViewById<TextView>(R.id.tvTelepon)
        val tvAlamat = view.findViewById<TextView>(R.id.tvAlamat)
    }
    interface OnAdapterListener{
        fun onClick(result: SekolahModel)
    }

}