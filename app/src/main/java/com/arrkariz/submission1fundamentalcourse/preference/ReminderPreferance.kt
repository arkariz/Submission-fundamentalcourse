package com.arrkariz.submission1fundamentalcourse.preference

import android.content.Context
import com.arrkariz.submission1fundamentalcourse.model.Reminder

class ReminderPreferance(context: Context) {
    companion object{
        const val PREFS_NAME = "reminder_pref"
        private const val REMINDER = "isRemind"
    }
    private val preferance = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun  setReminder(value: Reminder){
        val editor = preferance.edit()
        editor.putBoolean(REMINDER, value.isReminded)
        editor.apply()
    }

    fun getReminder(): Reminder{
        val model = Reminder()
        model.isReminded = preferance.getBoolean(REMINDER, false)
        return model
    }
}