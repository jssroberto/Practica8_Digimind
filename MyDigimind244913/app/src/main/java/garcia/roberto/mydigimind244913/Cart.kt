package garcia.roberto.mydigimind244913

import java.io.Serializable

object Cart : Serializable{
    private var reminders = ArrayList<Reminder>()

    fun addReminder(reminder: Reminder): Boolean{
        return reminders.add(reminder)
    }

    fun getReminders(): List<Reminder>{
        return reminders
    }

    @JvmStatic
    private fun readResolve(): Any {
        return this
    }
}