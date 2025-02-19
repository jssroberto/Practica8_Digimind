package garcia.roberto.mydigimind244913

import java.io.Serializable

class Cart : Serializable{
    private var reminders = ArrayList<Reminder>()

    fun addReminder(reminder: Reminder): Boolean{
        return reminders.add(reminder)
    }
}