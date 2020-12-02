package com.dam2.simondice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var estado: Boolean
        val btn_red: Button = findViewById(R.id.btRojo)
        val btn_yellow: Button = findViewById(R.id.btAmarillo)
        val btn_blue: Button = findViewById(R.id.btAzul)
        val btn_green: Button = findViewById(R.id.btVerde)
        val btn_start: Button = findViewById(R.id.btIniciar)
        val btn_check: Button = findViewById(R.id.btCheck)

        val Btnscolor = listOf(btn_green, btn_red, btn_yellow, btn_blue)

        val toast = Toast.makeText(applicationContext,"Comienza el juego!", Toast.LENGTH_SHORT)
        val toast1 = Toast.makeText(applicationContext,"Perdiste wey!", Toast.LENGTH_SHORT)

        val ModeloJuego by viewModels<MyViewModel>()

        ModeloJuego.estadoJuego.observe(this, Observer {
            estado = false
            if (estado) {
                btn_green.isEnabled = false
                btn_red.isEnabled = false
                btn_yellow.isEnabled = false
                btn_blue.isEnabled = false
                btn_check.isEnabled = false
            } else {
                btn_green.isEnabled = true
                btn_red.isEnabled = true
                btn_yellow.isEnabled = true
                btn_yellow.isEnabled = true
                btn_check.isEnabled = true
            }
        })

        ModeloJuego.secuenciaJuego.observe(this, Observer {
            ModeloJuego.mostrarSec(Btnscolor)
        })

        btn_start.setOnClickListener {
            toast.show()
            ModeloJuego.iniciarJuego()
        }

        btn_check.setOnClickListener {
            if (!ModeloJuego.comprobarSec()) {
                toast1.show()
            }
        }
        btn_green.setOnClickListener {
            ModeloJuego.añadirSecuencia(1)
        }
        btn_red.setOnClickListener {
            ModeloJuego.añadirSecuencia(2)
        }
        btn_yellow.setOnClickListener {
            ModeloJuego.añadirSecuencia(3)
        }
        btn_blue.setOnClickListener {
            ModeloJuego.añadirSecuencia(4)
        }
    }
}