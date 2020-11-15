package com.dam2.simondice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_verde : Button = findViewById(R.id.btVerde)
        val bt_rojo : Button = findViewById(R.id.btRojo)
        val bt_amarillo : Button = findViewById(R.id.btAmarillo)
        val bt_azul : Button = findViewById(R.id.btAzul)
        val bt_start : Button = findViewById(R.id.btIniciar)
        val bt_check : Button = findViewById(R.id.btCheck)
        val secuenciaColores: MutableList<Int> = mutableListOf()
        val secUsuario: MutableList<Int> = mutableListOf()
        var finished  = false
        val toast = Toast.makeText(applicationContext,"Comienza el juego!", Toast.LENGTH_SHORT)
        val toast1 = Toast.makeText(applicationContext,"Perdiste wey!", Toast.LENGTH_SHORT)

        bt_start.setOnClickListener{
            finished = false
            reset(secuenciaColores,secUsuario)
            añadirSec(secuenciaColores)
            toast.show()
            mostrarSec(secuenciaColores)
        }

        bt_check.setOnClickListener{
            if(!finished){
                if(comprobarSec(secuenciaColores,secUsuario)){
                    añadirSec(secuenciaColores)
                    secUsuario.clear()
                    mostrarSec(secuenciaColores)
            }else{
                finished = true
                toast1.show()
            }
            }
        }

        bt_verde.setOnClickListener{
            añadirSecUsuario(secUsuario,1)
        }

        bt_rojo.setOnClickListener{
            añadirSecUsuario(secUsuario,2)
        }

        bt_amarillo.setOnClickListener{
            añadirSecUsuario(secUsuario,3)
        }

        bt_azul.setOnClickListener{
            añadirSecUsuario(secUsuario,4)
        }
    }

    fun añadirSec(sec : MutableList<Int>)  {
        val numb = kotlin.random.Random.nextInt(4) + 1
        sec.add(numb)
    }

    fun comprobarSec(sec : MutableList<Int>, secUsr : MutableList<Int>) : Boolean {
        return sec == secUsr
    }

    fun reset(sec: MutableList<Int>, secUsr: MutableList<Int>){
        sec.clear()
        secUsr.clear()
    }

    fun añadirSecUsuario(secUsr: MutableList<Int>, color: Int){
        when(color){
            1 -> secUsr.add(1)
            2 -> secUsr.add(2)
            3 -> secUsr.add(3)
            else -> secUsr.add(4)
        }
    }

    fun mostrarSec(sec: MutableList<Int>){
        for (color in sec){
            when(color){
                1 -> Toast.makeText(applicationContext,"Verde", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(applicationContext,"Rojo", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(applicationContext,"Amarillo", Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(applicationContext,"Azul", Toast.LENGTH_SHORT).show()
            }
        }
    }
}