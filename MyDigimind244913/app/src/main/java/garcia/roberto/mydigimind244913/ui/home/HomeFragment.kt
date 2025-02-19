package garcia.roberto.mydigimind244913.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import garcia.roberto.mydigimind244913.Cart
import garcia.roberto.mydigimind244913.R
import garcia.roberto.mydigimind244913.Reminder
import garcia.roberto.mydigimind244913.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var reminderAdapter: ReminderAdapter
    private lateinit var reminders: List<Reminder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        reminders = Cart.getReminders()

        val gridView = binding.root.findViewById<RecyclerView>(R.id.grid_view)

        gridView.layoutManager = GridLayoutManager(context, 3)
        gridView.addItemDecoration(MarginDecoration(16))

        reminderAdapter = ReminderAdapter(reminders)
        gridView.adapter = reminderAdapter

        return binding.root

    }


    private class ReminderAdapter(private val reminders: List<Reminder>) :
        RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {
        class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvTitle: TextView = itemView.findViewById(R.id.txt_reminder)
            val tvDays: TextView = itemView.findViewById(R.id.txt_reminder_days)
            val tvTime: TextView = itemView.findViewById(R.id.txt_reminder_time)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.reminder, parent, false)
            return ReminderViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
            val reminder = reminders[position]
            holder.tvTitle.text = reminder.name
            holder.tvDays.text = formatDays(reminder.days)
            holder.tvTime.text = reminder.time
        }

        override fun getItemCount(): Int {
            return reminders.size
        }

        private fun formatDays(days: List<String>): String {
            return when {
                days.size == 7 -> "Everyday"
                else -> {
                    val dayInitials = mapOf(
                        "Monday" to "M",
                        "Tuesday" to "T",
                        "Wednesday" to "W",
                        "Thursday" to "T",
                        "Friday" to "F",
                        "Saturday" to "S",
                        "Sunday" to "S"
                    )
                    val initials = days.mapNotNull { dayInitials[it] }.joinToString("-")
                    initials
                }
            }
        }
    }

    class MarginDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = margin
            outRect.right = margin
            outRect.top = margin
            outRect.bottom = margin
        }
    }




}