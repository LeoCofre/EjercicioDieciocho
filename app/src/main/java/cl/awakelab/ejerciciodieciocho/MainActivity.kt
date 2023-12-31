package cl.awakelab.ejerciciodieciocho

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cl.awakelab.ejerciciodieciocho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mSharedPreferences = getSharedPreferences("Cookie", Context.MODE_PRIVATE)
        binding.btGuardar.setOnClickListener {
            val texto = binding.etTexto.text.toString()
            val entero = binding.etEntero.text.toString().toInt()
            val decimal = binding.etDecimal.text.toString().toFloat()
            val switch = binding.switch1.isChecked

            guardarDatos(texto, entero, decimal, switch)
        }
        binding.btMostrar.setOnClickListener {
            mostrarDatos()
        }
        binding.btBorrar.setOnClickListener {
            borrarDatos()
        }
    }

    private fun guardarDatos(texto: String, entero: Int, decimal: Float, booleano: Boolean) {
        mSharedPreferences.edit().putString("My texto", texto).apply()
        mSharedPreferences.edit().putInt("My Entero", entero).apply()
        mSharedPreferences.edit().putFloat("My Float", decimal).apply()
        mSharedPreferences.edit().putBoolean("My Boolean", booleano).apply()
    }

    private fun mostrarDatos() {
        val texto = mSharedPreferences.getString("My texto", "")
        val entero = mSharedPreferences.getInt("My Entero", 0)
        val decimal = mSharedPreferences.getFloat("My Float", 0.0f)
        val booleano = mSharedPreferences.getBoolean("My Boolean", true)

        binding.tvTexto.text = texto
        binding.tvEntero.text = entero.toString()
        binding.tvDecimal.text = decimal.toString()
        binding.tvSwitch.text = booleano.toString()
        binding.switch1.isChecked = booleano


    }

    private fun borrarDatos() {
        binding.tvTexto.text = ""
        binding.tvEntero.text = ""
        binding.tvDecimal.text = ""
        binding.tvSwitch.text = ""
        binding.switch1.isChecked = false

        mSharedPreferences.edit().clear()
    }

}