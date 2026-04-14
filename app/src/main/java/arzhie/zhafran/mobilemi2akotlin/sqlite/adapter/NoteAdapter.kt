package arzhie.zhafran.mobilemi2akotlin.sqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import arzhie.zhafran.mobilemi2akotlin.R
import arzhie.zhafran.mobilemi2akotlin.sqlite.model.NoteModel
import kotlin.collections.get

class NoteAdapter(
    private val listNote: List<NoteModel>,
    private val onDeleteClick: (NoteModel) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ViewNote>() {

    class ViewNote(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvContent: TextView = view.findViewById(R.id.tvContent)
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewNote {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewNote(view)
    }

    override fun onBindViewHolder(holder: ViewNote, position: Int) {
        val data = listNote[position]

        holder.tvTitle.text = data.title
        holder.tvContent.text = data.content

        holder.btnDelete.setOnClickListener {
            onDeleteClick(data)
        }
    }

    override fun getItemCount(): Int = listNote.size
}