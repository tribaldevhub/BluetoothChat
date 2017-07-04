package com.glodanif.bluetoothchat

import com.glodanif.bluetoothchat.model.SettingsManager
import com.glodanif.bluetoothchat.presenter.ProfilePresenter
import com.glodanif.bluetoothchat.view.ProfileView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProfilePresenterUnitTest {

    @Mock
    lateinit var model: SettingsManager
    @Mock
    lateinit var view: ProfileView

    lateinit var presenter: ProfilePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ProfilePresenter(view, model)
    }

    @Test
    fun validation_emptyUserName() {
        Mockito.`when`(model.getUserName()).thenReturn("")
        ProfilePresenter(view, model).saveUser()
        Mockito.verify(view)?.showNotValidNameError()
    }

    @Test
    fun validation_forbiddenCharacters() {
        Mockito.`when`(model.getUserName()).thenReturn("Test#")
        ProfilePresenter(view, model).saveUser()
        Mockito.verify(view)?.showNotValidNameError()
    }

    @Test
    fun validation_longUserName() {
        Mockito.`when`(model.getUserName()).thenReturn("Test longer that 25 characters")
        ProfilePresenter(view, model).saveUser()
        Mockito.verify(view)?.showNotValidNameError()
    }

    @Test
    fun validation_validUsername() {
        Mockito.`when`(model.getUserName()).thenReturn("Test")
        ProfilePresenter(view, model).saveUser()
        Mockito.verify(view)?.redirectToConversations()
    }

    @Test
    fun color_preparePicker() {
        presenter.prepareColorPicker()
        Mockito.verify(view)?.showColorPicker(0)
    }

    @Test
    fun color_onColorPicked() {
        presenter.onColorPicked(0)
        Mockito.verify(view)?.displayUserData(uninitialized(), 0)
    }

    @Test
    fun onStart_displayProfile() {
        presenter.onStart()
        Mockito.verify(view)?.displayUserData(uninitialized(), 0)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}
