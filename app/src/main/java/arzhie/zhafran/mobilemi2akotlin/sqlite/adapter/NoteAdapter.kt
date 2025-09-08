package arzhie.zhafran.mobilemi2akotlin.sqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.NoteModel
import kotlin.collections.get

class NoteAdapter (
    private val listNote: List<NoteModel>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<NoteAdapter.ViewNote>()

{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.ViewNote {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note,parent,false)
        return ViewNote(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewNote, position: Int) {
        val data = listNote[position]
        holder.tvTitle.text = data.title
        holder.tvKontent.text = data.kontent

        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongClick(data, data.id)
            true
        }
    }

    override fun getItemCount(): Int = listNote.size
    class ViewNote(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvKontent = view.findViewById<TextView>(R.id.tvKontent)
    }
    interface OnAdapterListener{
        fun onClick(data: NoteModel)
        fun onLongClick(data: NoteModel, position: Int)
    }
}
