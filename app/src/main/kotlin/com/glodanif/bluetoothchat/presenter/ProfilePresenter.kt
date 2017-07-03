package com.glodanif.bluetoothchat.presenter

import android.support.annotation.ColorInt
import com.glodanif.bluetoothchat.model.SettingsManager
import com.glodanif.bluetoothchat.view.ProfileView

class ProfilePresenter(private val view: ProfileView, private val settings: SettingsManager) {

    @ColorInt
    private var currentColor = settings.getUserColor()
    private var currentName = settings.getUserName()

    fun saveUser() {
        settings.saveUserName(currentName)
        settings.saveUserColor(currentColor)
        view.redirectToConversations()
    }

    fun prepareColorPicker() {
        view.showColorPicker(currentColor)
    }

    fun onColorPicked(@ColorInt color: Int) {
        currentColor = color
        view.displayUserData(currentName, color)
    }

    fun onNameChanged(name: String) {
        currentName = name
        view.displayUserData(name, currentColor)
    }

    fun onStart() {
        view.displayUserData(currentName, currentColor)
    }
}
