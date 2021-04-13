package com.arrkariz.submission1fundamentalcourse.view.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arrkariz.submission1fundamentalcourse.R
import com.arrkariz.submission1fundamentalcourse.databinding.ActivitySettingsBinding
import com.arrkariz.submission1fundamentalcourse.model.Reminder
import com.arrkariz.submission1fundamentalcourse.preference.ReminderPreferance
import com.arrkariz.submission1fundamentalcourse.receiver.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.*

class ReminderActivity : AppCompatActivity(), View.OnClickListener,TimePickerFragment.DialogTimeListener {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    companion object {
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding?.btnRepeatingTime?.setOnClickListener(this)

        val reminderPreferance = ReminderPreferance(this)

        if (reminderPreferance.getReminder().isReminded){
            binding.switchOne.isChecked = true
        } else{
            binding.switchOne.isChecked = false
        }

        alarmReceiver = AlarmReceiver()
        binding.switchOne.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                saveReminder(true)
                val repeatTime = binding?.tvRepeatingTime?.text.toString()
                alarmReceiver.setRepeatedAlarm(this, "RepeatingAlarm", repeatTime, "Github App Reminder")
            } else{
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_repeating_time -> {
                val timePickerFragmentRepeat = TimePickerFragment()
                timePickerFragmentRepeat.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreferance = ReminderPreferance(this)
        reminder = Reminder()
        reminder.isReminded = state
        reminderPreferance.setReminder(reminder)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            TIME_PICKER_REPEAT_TAG -> binding?.tvRepeatingTime?.text = dateFormat.format(calendar.time)
        }
    }
}