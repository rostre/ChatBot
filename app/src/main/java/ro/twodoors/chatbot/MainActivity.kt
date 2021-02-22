package ro.twodoors.chatbot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ro.twodoors.chatbot.databinding.ActivityMainBinding
import ro.twodoors.chatbot.fragments.ChatBotFragment
import ro.twodoors.chatbot.fragments.HomeFragment
import ro.twodoors.chatbot.utils.exitCircularReveal
import ro.twodoors.chatbot.utils.open

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.open {
            replace(R.id.container, HomeFragment())
        }
    }

    override fun onBackPressed() {
        with(supportFragmentManager.findFragmentById(R.id.container)) {
            if (this as? ChatBotFragment != null) {
                if (this.posX == null || this.posY == null) {
                    super.onBackPressed()
                } else {
                    this.view?.exitCircularReveal(this.posX!!, this.posY!!) {
                        super.onBackPressed()
                    } ?: super.onBackPressed()
                }
            } else {
                super.onBackPressed()
            }
        }
    }
}