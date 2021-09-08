package com.devdays.bloodcare.ui.find

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdays.bloodcare.R
import com.devdays.bloodcare.util.Event

class FindViewModel : ViewModel() {
    val mFindFullName: ObservableField<String> = ObservableField("")
    val mFindMobileNumber: ObservableField<String> = ObservableField("")
    val mFindRelation: ObservableField<String> = ObservableField("")
    val mFindBloodGroup: ObservableField<String> = ObservableField("")
    val mFindLocation: ObservableField<String> = ObservableField("")

    private val _findSnackbarMessage = MutableLiveData<Event<Int>>()
    val mFindSnackbarMessage: LiveData<Event<Int>> = _findSnackbarMessage

    private val _findSubmitRequestEvent = MutableLiveData<Event<Unit>>()
    val mFindSubmitRequestEvent: LiveData<Event<Unit>> = _findSubmitRequestEvent

    fun showFindSnackbar(mMessage: Int) {
        _findSnackbarMessage.value = Event(mMessage)
    }

    fun onClickFindSubmitRequest() {
        when {
            mFindFullName.get()?.isEmpty() == true -> {
                showFindSnackbar(R.string.text_error_full_name)
            }
            mFindMobileNumber.get()?.isEmpty() == true -> {
                showFindSnackbar(R.string.text_error_field_empty)
            }
            mFindMobileNumber.get()?.length!! < 10 -> {
                showFindSnackbar(R.string.text_label_invalid_mobile_number)
            }
            mFindRelation.get()?.isEmpty() == true -> {
                showFindSnackbar(R.string.text_error_relation)
            }
            mFindBloodGroup.get()?.isEmpty() == true -> {
                showFindSnackbar(R.string.text_error_blood_group)
            }
            mFindLocation.get()?.isEmpty() == true -> {
                showFindSnackbar(R.string.text_error_location)
            }
            else -> {
                _findSubmitRequestEvent.value = Event(Unit)
            }
        }
    }
}