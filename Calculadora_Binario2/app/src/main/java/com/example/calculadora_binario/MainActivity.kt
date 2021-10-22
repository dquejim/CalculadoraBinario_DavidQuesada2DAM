package com.example.calculadora_binario

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var num1: Double = 0.0
    private var num2: Double = 0.0

    private var num1bin:Long = 0
    private var num2bin:Long = 0
    private var num1dec:Double = 0.0
    private var num2dec:Double = 0.0

    private var num1hex:String = ""
    private var num2hex:String = ""

    private var operacion: Int = 0
    private var resultado:Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            unoBoton.setOnClickListener { pulsarBotonDecimal(digito = "1") }
            dosBoton.setOnClickListener { pulsarBotonDecimal(digito = "2") }
            tresBoton.setOnClickListener { pulsarBotonDecimal(digito = "3") }
            cuatroBoton.setOnClickListener { pulsarBotonDecimal(digito = "4") }
            cincoBoton.setOnClickListener { pulsarBotonDecimal(digito = "5") }
            seisBoton.setOnClickListener { pulsarBotonDecimal(digito = "6") }
            sieteBoton.setOnClickListener { pulsarBotonDecimal(digito = "7") }
            ochoBoton.setOnClickListener { pulsarBotonDecimal(digito = "8") }
            nueveBoton.setOnClickListener { pulsarBotonDecimal(digito = "9") }
            constrai.setOnClickListener { pulsarBotonDecimal(digito = "0") }

            puntoBoton.setOnClickListener { pulsarBotonDecimal(digito = ".") }
            igualBoton.setOnClickListener { pulsarBotonDecimal(digito = "=") }

            masBoton.setOnClickListener { operarDecimal(Suma) }
            menosBoton.setOnClickListener { operarDecimal(Resta) }
            porBoton.setOnClickListener { operarDecimal(Multiplicacion) }
            entreBoton.setOnClickListener { operarDecimal(Division) }


            clearBoton.setOnClickListener {
                num1 = 0.0
                num2 = 0.0
                ops.text = ""
                ops.text = ""
                operacion = No_Operacion
            }

            igualBoton.setOnClickListener {
                resolverDecimal()
            }
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            binBoton.setOnClickListener(){
                dosBoton.isEnabled=false
                tresBoton.isEnabled=false
                cuatroBoton.isEnabled=false
                cincoBoton.isEnabled=false
                seisBoton.isEnabled=false
                sieteBoton.isEnabled=false
                ochoBoton.isEnabled=false
                nueveBoton.isEnabled=false
                puntoBoton.isEnabled=false
                aBoton.isEnabled=false
                bBoton.isEnabled=false
                cBoton.isEnabled=false
                dBoton.isEnabled=false
                eBoton.isEnabled=false
                fBoton.isEnabled=false

                ceroBoton.setOnClickListener() { pulsarBotonBinario("0") }
                unoBoton.setOnClickListener() { pulsarBotonBinario("1") }

                masBoton.setOnClickListener { operarBinario(Suma) }
                menosBoton.setOnClickListener { operarBinario(Resta) }
                porBoton.setOnClickListener { operarBinario(Multiplicacion) }
                entreBoton.setOnClickListener { operarBinario(Division) }
                clearBoton.setOnClickListener(){
                    restart()
                }
                igualBoton.setOnClickListener(){resolverBinario()}
                num1dec = 0.0
                num1hex=""
                num2dec = 0.0
                num2hex=""
                opsBinario.text=""
            }

            hexBoton.setOnClickListener(){
                clearBoton.isEnabled=true
                dosBoton.isEnabled=true
                tresBoton.isEnabled=true
                cuatroBoton.isEnabled=true
                cincoBoton.isEnabled=true
                seisBoton.isEnabled=true
                sieteBoton.isEnabled=true
                ochoBoton.isEnabled=true
                nueveBoton.isEnabled=true
                puntoBoton.isEnabled=true
                aBoton.isEnabled=true
                bBoton.isEnabled=true
                cBoton.isEnabled=true
                dBoton.isEnabled=true
                eBoton.isEnabled=true
                fBoton.isEnabled=true

                ceroBoton.setOnClickListener() { pulsarBotonHexadecimal("0") }
                unoBoton.setOnClickListener() { pulsarBotonHexadecimal("1") }
                dosBoton.setOnClickListener() { pulsarBotonHexadecimal("2") }
                tresBoton.setOnClickListener() { pulsarBotonHexadecimal("3") }
                cuatroBoton.setOnClickListener() { pulsarBotonHexadecimal("4") }
                cincoBoton.setOnClickListener() { pulsarBotonHexadecimal("5") }
                seisBoton.setOnClickListener() { pulsarBotonHexadecimal("6") }
                sieteBoton.setOnClickListener() { pulsarBotonHexadecimal("7") }
                ochoBoton.setOnClickListener() { pulsarBotonHexadecimal("8") }
                nueveBoton.setOnClickListener() { pulsarBotonHexadecimal("9") }

                aBoton.setOnClickListener() { pulsarBotonHexadecimal("A") }
                bBoton.setOnClickListener() { pulsarBotonHexadecimal("B") }
                cBoton.setOnClickListener() { pulsarBotonHexadecimal("C") }
                dBoton.setOnClickListener() { pulsarBotonHexadecimal("D") }
                eBoton.setOnClickListener() { pulsarBotonHexadecimal("E") }
                fBoton.setOnClickListener() { pulsarBotonHexadecimal("F") }

                masBoton.setOnClickListener { operarBinario(Suma) }
                menosBoton.setOnClickListener { operarBinario(Resta) }
                porBoton.setOnClickListener { operarBinario(Multiplicacion) }
                entreBoton.setOnClickListener { operarBinario(Division) }
                igualBoton.setOnClickListener(){resolverHexadecimal() }
                clearBoton.setOnClickListener(){
                    restart()
                }
                num1bin = 0
                num2bin = 0
                num1dec = 0.0
                num2dec = 0.0
                opsBinario.text=""
            }
            }

    }
    private fun restart(){
        finish()
        startActivity(getIntent())
    }

    private  fun resolverDecimal() {
        var resultado = when (operacion) {
            Suma -> num1 + num2
            Resta -> num1 - num2
            Multiplicacion -> num1 * num2
            Division -> num1 / num2
            else -> 0
        }
        ops.text = resultado.toString();
        num1=resultado.toDouble()
    }

    private  fun resolverBinario() {

        var resultado = when (operacion) {
            Suma -> num1dec + num2dec
            Resta -> num1dec - num2dec
            Multiplicacion -> num1dec * num2dec
            Division -> num1dec / num2dec
            else -> 0
        }

        opsBinario.text= conversorBinario(resultado.toInt()).toString()
        num1dec = resultado as Double
    }

    private fun resolverHexadecimal(){
        var resultado = when (operacion) {
            Suma -> num1dec + num2dec
            Resta -> num1dec - num2dec
            Multiplicacion -> num1dec * num2dec
            Division -> num1dec / num2dec
            else -> 0
        }

        opsBinario.text= conversorDecimalaHexadecimal(resultado.toDouble())
        num1dec = resultado as Double

    }

    private fun pulsarBotonBinario(digito: String) {
        opsBinario.text = "${opsBinario.text}$digito"

        if (operacion == No_Operacion ) {
            num1bin = opsBinario.text.toString().toLong();
            num1dec = conversorDecimalaBinario(num1bin)

        } else {
            num2bin = opsBinario.text.toString().toLong();
            num2dec = conversorDecimalaBinario(num2bin)
        }
    }

    private fun pulsarBotonDecimal(digito: String) {
        ops.text = "${ops.text}$digito"

        if (operacion == No_Operacion ) {
            num1 = ops.text.toString().toDouble();

        } else {
            num2 = ops.text.toString().toDouble();
        }
    }

    private fun pulsarBotonHexadecimal(digito: String) {
        opsBinario.text = "${opsBinario.text}$digito"

        if (operacion == No_Operacion ) {
            num1hex = opsBinario.text.toString();
            num1dec = conversorHexadecimal(num1hex).toDouble()

        } else {
            num2hex = opsBinario.text.toString();
            num2dec = conversorHexadecimal(num2hex).toDouble()
        }
    }


    private fun operarBinario(operacion: Int) {
        this.operacion = operacion
        opsBinario.text=""
    }

    private fun operarDecimal(operacion: Int) {
        this.operacion = operacion
        ops.text=""
    }


    fun conversorDecimalaBinario(binario: Long): Double {
        var binario = binario
        var decimal = 0.0
        var i = 0
        var remainder: Long

        while (binario.toInt() != 0) {
            remainder = binario % 10
            binario /= 10
            decimal += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimal
    }

    fun conversorBinario(n: Int): Long {
        var n = n
        var numBinario: Long = 0
        var remainder: Int
        var i = 1
        var step = 1

        while (n != 0) {
            remainder = n % 2
            System.out.printf("Step %d: %d/2, Remainder = %d, Quotient = %d\n", step++, n, remainder, n / 2)
            n /= 2
            numBinario += (remainder * i).toLong()
            i *= 10
        }
        return numBinario
    }

    fun conversorDecimalaHexadecimal(decimal:Double):String{
        var x = Integer.toHexString(decimal.toInt())
        return x
    }

    fun conversorHexadecimal(hexa: String): Int {
        return Integer.parseInt(hexa,16)
    }


    companion object {
        const val Suma = 1
        const val Resta = 2
        const val Multiplicacion = 3
        const val Division = 4
        const val No_Operacion = 0
    }
}


