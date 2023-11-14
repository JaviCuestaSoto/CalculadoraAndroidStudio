package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculadora.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var texto: String = ""
    private var reg: String = ""

    override fun onSaveInstanceState(outState: Bundle) {
        if (outState != null) {
            super.onSaveInstanceState(outState)
            outState?.putString("texto", texto)
            outState?.putString("registro", reg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            texto = savedInstanceState.getString("texto").toString()
            binding.operaciones.text = texto
            reg = savedInstanceState.getString("registro").toString()
            binding.registro.text = reg
        }

        binding.cero.setOnClickListener(this)
        binding.uno.setOnClickListener(this)
        binding.dos.setOnClickListener(this)
        binding.tres.setOnClickListener(this)
        binding.cuatro.setOnClickListener(this)
        binding.cinco.setOnClickListener(this)
        binding.seis.setOnClickListener(this)
        binding.siete.setOnClickListener(this)
        binding.ocho.setOnClickListener(this)
        binding.nueve.setOnClickListener(this)

        binding.ac.setOnClickListener(this)
        binding.negativoPositivo.setOnClickListener(this)
        binding.porcentaje.setOnClickListener(this)
        binding.coma.setOnClickListener(this)

        binding.division.setOnClickListener(this)
        binding.multiplicar.setOnClickListener(this)
        binding.resta.setOnClickListener(this)
        binding.suma.setOnClickListener(this)
        binding.igual.setOnClickListener(this)

        binding.raizCuadrada?.setOnClickListener(this)
        binding.PI?.setOnClickListener(this)
        binding.seno?.setOnClickListener(this)
        binding.coseno?.setOnClickListener(this)
        binding.tangente?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.cero.id-> texto += "0"
            binding.uno.id-> texto += "1"
            binding.dos.id-> texto += "2"
            binding.tres.id-> texto += "3"
            binding.cuatro.id-> texto += "4"
            binding.cinco.id-> texto += "5"
            binding.seis.id-> texto += "6"
            binding.siete.id-> texto += "7"
            binding.ocho.id-> texto += "8"
            binding.nueve.id-> texto += "9"

            binding.ac.id-> {
                texto = ""
                reg = ""
            }
            binding.negativoPositivo.id-> texto = formatoResultado(texto.toDouble() * -1).toString()
            binding.porcentaje.id-> texto = formatoResultado(texto.toDouble() * 0.01).toString()
            binding.coma.id-> texto += "."

            binding.division.id-> texto += "/"
            binding.multiplicar.id-> texto += "x"
            binding.resta.id-> texto += "-"
            binding.suma.id-> texto += "+"
            //binding.igual.id-> texto += "="

            binding.raizCuadrada?.id-> texto = limitarDecimales(formatoResultado(Math.sqrt(texto.toDouble())). toDouble(), 2)
            binding.PI?.id-> texto += Math.PI.toString()
            binding.seno?.id-> texto = limitarDecimales(formatoResultado(Math.sqrt(texto.toDouble())). toDouble(), 2)
            binding.coseno?.id-> texto = limitarDecimales(formatoResultado(Math.sqrt(texto.toDouble())). toDouble(), 2)
            binding.tangente?.id-> texto = limitarDecimales(formatoResultado(Math.sqrt(texto.toDouble())). toDouble(), 2)

            binding.igual.id-> {

                val input = binding.operaciones.text
                val cuenta = binding.registro.text

                if (input.isNotEmpty()) {
                    if (input.contains("+")) {
                        val operacionDividida = input.split("+")
                        if (operacionDividida.size == 2) {
                            val op1 = operacionDividida[0].toDouble()
                            val op2 = operacionDividida[1].toDouble()
                            val result = op1 + op2
                            reg = texto
                            texto = limitarDecimales(formatoResultado(result).toDouble(), 2).toString()
                        }
                    } else if (input.contains("x")) {
                        val operacionDividida = input.split("x")
                        if (operacionDividida.size == 2) {
                            val op1 = operacionDividida[0].toDouble()
                            val op2 = operacionDividida[1].toDouble()
                            val result = op1 * op2
                            reg = texto
                            texto = limitarDecimales(formatoResultado(result).toDouble(), 2).toString()
                        }
                    } else if (input.contains("/")) {
                        val operacionDividida = input.split("/")
                        if (operacionDividida.size == 2) {
                            val op1 = operacionDividida[0].toDouble()
                            val op2 = operacionDividida[1].toDouble()
                            val result = op1 / op2
                            reg = texto
                            texto =
                                limitarDecimales(formatoResultado(result).toDouble(), 2).toString()
                        }
                    } else if (input.contains("-")) {
                        val cleanedInput = input.removePrefix("-")
                        val operacionDividida = cleanedInput.split("-")
                        val menosPrimero = input.startsWith("-")

                        if (menosPrimero) {
                            val op1 = operacionDividida[0].toDouble()
                            val op2 = operacionDividida[1].toDouble()
                            val result = -1 * (op1 + op2)
                            reg = texto
                            texto = limitarDecimales(formatoResultado(result).toDouble(), 2).toString()
                        } else if (operacionDividida.size == 2) {
                            val op1 = operacionDividida[0].toDouble()
                            val op2 = operacionDividida[1].toDouble()
                            val result = op1 - op2
                            reg = texto
                            texto = limitarDecimales(formatoResultado(result).toDouble(), 2).toString()
                        }
                    } else {
                        if (cuenta.contains("+")) {
                            val operacionDividida = cuenta.split("+")
                            if (operacionDividida.size == 2) {
                                val op1 = input.toString().toDouble()
                                val op2 = operacionDividida[1].toDouble()
                                val result = op1 + op2
                                reg = formatoResultado(op1) + ("+") + formatoResultado(op2)
                                texto = limitarDecimales(formatoResultado(result).toDouble(), 2)
                            }
                        } else if (cuenta.contains("-")) {
                            val operacionDividida = cuenta.split("-")
                            if (operacionDividida.size == 2) {
                                val op1 = input.toString().toDouble()
                                val op2 = operacionDividida[1].toDouble()
                                val result = op1 - op2
                                reg = formatoResultado(op1) + ("-") + formatoResultado(op2)
                                texto = limitarDecimales(formatoResultado(result).toDouble(), 2)
                            }
                        } else if (cuenta.contains("x")) {
                            val operacionDividida = cuenta.split("x")
                            if (operacionDividida.size == 2) {
                                val op1 = input.toString().toDouble()
                                val op2 = operacionDividida[1].toDouble()
                                val result = op1 * op2
                                reg = formatoResultado(op1) + ("x") + formatoResultado(op2)
                                texto = limitarDecimales(formatoResultado(result).toDouble(), 2)
                            }
                        } else if (cuenta.contains("/")) {
                            val operacionDividida = cuenta.split("/")
                            if (operacionDividida.size == 2) {
                                val op1 = input.toString().toDouble()
                                val op2 = operacionDividida[1].toDouble()
                                val result = op1 / op2
                                reg = formatoResultado(op1) + ("/") + formatoResultado(op2)
                                texto = limitarDecimales(formatoResultado(result).toDouble(), 2)
                            }
                        }
                    }
                }
            }
        }
        binding.operaciones.text = texto
        binding.registro.text = reg

    }

    fun formatoResultado (valor: Double): String {
        if (valor % 1 == 0.0) {
            return valor.toInt().toString()
        } else {
            return valor.toString()
        }
    }

    fun limitarDecimales(numero: Double, numeroDeDecimales: Int): String {
        val formato = DecimalFormat("#." + "#".repeat(numeroDeDecimales))
        return formato.format(numero)
    }
}