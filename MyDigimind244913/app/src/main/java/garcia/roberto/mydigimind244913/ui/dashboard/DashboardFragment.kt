package garcia.roberto.mydigimind244913.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import garcia.roberto.mydigimind244913.Cart
import garcia.roberto.mydigimind244913.R
import garcia.roberto.mydigimind244913.Reminder
import java.util.Calendar

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var edtReminderName: EditText
    private lateinit var chkMonday: CheckBox
    private lateinit var chkTuesday: CheckBox
    private lateinit var chkWednesday: CheckBox
    private lateinit var chkThursday: CheckBox
    private lateinit var chkFriday: CheckBox
    private lateinit var chkSaturday: CheckBox
    private lateinit var chkSunday: CheckBox
    private lateinit var btnSelectTime: Button
    private var selectedTime = ""
    private lateinit var txtSelectedTime: TextView
    private lateinit var btnSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtReminderName = view.findViewById(R.id.edt_reminder_name)
        chkMonday = view.findViewById(R.id.chk_monday)
        chkTuesday = view.findViewById(R.id.chk_tuesday)
        chkWednesday = view.findViewById(R.id.chk_wednesday)
        chkThursday = view.findViewById(R.id.chk_thursday)
        chkFriday = view.findViewById(R.id.chk_friday)
        chkSaturday = view.findViewById(R.id.chk_saturday)
        chkSunday = view.findViewById(R.id.chk_sunday)
        btnSelectTime = view.findViewById(R.id.btn_select_time)
        txtSelectedTime = view.findViewById(R.id.txt_selected_time)
        btnSubmit = view.findViewById(R.id.btn_submit)

        btnSelectTime.setOnClickListener {
            showTimePicker()
        }

        btnSubmit.setOnClickListener {
            val selectedDays = getSelectedDays()
            val habit = edtReminderName.text.toString()
            
            if (habit.isEmpty() || selectedDays.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val newReminder = Reminder(habit, selectedDays, selectedTime)
            Cart.addReminder(newReminder)

            Toast.makeText(requireContext(), "Reminder added successfully", Toast.LENGTH_SHORT).show()
            
        }


    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            txtSelectedTime.text = "Selected Time: $selectedTime"
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun getSelectedDays(): MutableList<String> {
        val selectedDaysList = mutableListOf<String>()

        if (chkMonday.isChecked) selectedDaysList.add("Monday")
        if (chkTuesday.isChecked) selectedDaysList.add("Tuesday")
        if (chkWednesday.isChecked) selectedDaysList.add("Wednesday")
        if (chkThursday.isChecked) selectedDaysList.add("Thursday")
        if (chkFriday.isChecked) selectedDaysList.add("Friday")
        if (chkSaturday.isChecked) selectedDaysList.add("Saturday")
        if (chkSunday.isChecked) selectedDaysList.add("Sunday")

        return selectedDaysList
    }

}