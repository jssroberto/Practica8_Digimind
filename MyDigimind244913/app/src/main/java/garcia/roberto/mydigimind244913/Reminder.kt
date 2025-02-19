package garcia.roberto.mydigimind244913

import java.io.Serializable

data class Reminder( var name: String, var days: MutableList<String>, var time:String) : Serializable{
}