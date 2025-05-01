package com.example.hanumanchalisa

import android.content.ClipData.Item
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hanumanchalisa.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
//    private val prefs: SharedPreferences by lazy {
//        getSharedPreferences("app_preferences", MODE_PRIVATE)
//    }
    private fun setLocale(languageCode : String) {
        val local = Locale(languageCode)
        Locale.setDefault(local)
        val config = resources.configuration
        config.setLocale(local)

        val context = createConfigurationContext(config)
//        baseContext.createConfigurationContext(config)
//        applyOverrideConfiguration(config)
        recreate()
    }
//    val currentLanguage = prefs.getString("language","en") ?: "en"
//    setLocale(currentLanguage)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = listOf(
            getString(R.string.jai_shree_ram),
            getString(R.string.sh1),
            getString(R.string.sh2),
            getString(R.string.sh3),
            getString(R.string.sh4),
            getString(R.string.sh5),
            getString(R.string.sh6),
            getString(R.string.sh7),
            getString(R.string.sh8),
            getString(R.string.sh9),
            getString(R.string.sh10),
            getString(R.string.sh11),
            getString(R.string.sh12),
            getString(R.string.sh13),
            getString(R.string.sh14),
            getString(R.string.sh15),
            getString(R.string.sh16),
            getString(R.string.sh17),
            getString(R.string.sh18),
            getString(R.string.sh19),
            getString(R.string.sh20),
            getString(R.string.sh21),
            getString(R.string.sh22),
            getString(R.string.sh23),
            getString(R.string.jai_shree_ram)
        ) // Added all data in a list

        binding.button.setOnClickListener {
            showPopupMenu(it)
        }

        // Setup RecyclerView with adapter and layout manager
        binding.rv.apply {
            adapter = rvAdapter(data, this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    private fun showPopupMenu(view : View) {
        val popupMenu = PopupMenu(this,view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.menu,popupMenu.menu)
        val hindiMenuItem = popupMenu.menu.findItem(R.id.hindi)
        hindiMenuItem.title = if (Locale.getDefault().language == "hi") "English" else "हिन्दी"
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.hindi -> {
                    val locale = Locale.getDefault().language
                        if(locale == "hi") {
                            setLocale("en")
                            hindiMenuItem.title = "हिन्दी"
//                            prefs.edit().putString("language", "en").apply()
                        }
                        else {
                            setLocale("hi")
                            hindiMenuItem.title = "English"
//                            prefs.edit().putString("language", "hi").apply()
                        }
                    true
                }

                R.id.settings -> {
                    true
                }
                R.id.blessings -> {
                    Toast.makeText(this,
                        getString(R.string.hanuman_ji_blessed_you), Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }


}