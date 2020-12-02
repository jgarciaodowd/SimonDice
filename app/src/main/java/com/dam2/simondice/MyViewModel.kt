package com.dam2.simondice

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MyViewModel : ViewModel() {

    val secuenciaJuego = MutableLiveData<MutableList<Int>>()
    val secuenciaUsuario = MutableLiveData<MutableList<Int>>()
    val estadoJuego = MutableLiveData<Boolean>()

    init {
        secuenciaUsuario.value = mutableListOf<Int>()
        secuenciaJuego.value = mutableListOf<Int>()
        estadoJuego.value = true
    }

    fun iniciarJuego() {
        estadoJuego.value = false;
        resetear()
        añadirASec()
    }

    private fun añadirASec() {
        val numb = Random.nextInt(4) + 1
        secuenciaJuego.value?.add(numb)
        secuenciaJuego.postValue(secuenciaJuego.value)
    }

    fun comprobarSec(): Boolean {
        var ret = false
        if (secuenciaJuego.value == secuenciaUsuario.value && estadoJuego.value == false) {
            añadirASec()
            secuenciaUsuario.value?.clear()
            ret = true;
        } else {
            estadoJuego.value = true;
        }
        return ret;
    }

    private fun resetear() {
        secuenciaJuego.value?.clear()
        secuenciaUsuario.value?.clear()
    }

    fun añadirSecuencia(color: Int) {
        when (color) {
            1 -> secuenciaUsuario.value?.add(1)
            2 -> secuenciaUsuario.value?.add(2)
            3 -> secuenciaUsuario.value?.add(3)
            else -> secuenciaUsuario.value?.add(4)
        }
    }

    fun cogerSec(): MutableList<Int> {
        return secuenciaJuego.value!!
    }

    fun mostrarSec(listButton: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            for (colors in secuenciaJuego.value!!) {
                listButton[colors-1].isPressed = true
                delay(1000)
                listButton[colors-1].isPressed = false
            }
        }
    }
}