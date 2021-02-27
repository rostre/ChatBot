package ro.twodoors.chatbot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ro.twodoors.chatbot.R
import ro.twodoors.chatbot.databinding.ActivityMainBinding
import ro.twodoors.chatbot.utils.exitCircularReveal
import ro.twodoors.chatbot.utils.open

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.open {
                replace(R.id.container, HomeFragment())
        }
    }

    override fun onBackPressed() {
        with(supportFragmentManager.findFragmentById(R.id.container)) {
            if (this as? ChatBotFragment != null) {
                if (this.posX == 0 || this.posY == 0) {
                    super.onBackPressed()
                } else {
                    this.view?.exitCircularReveal(this.posX, this.posY) {
                        super.onBackPressed()
                    } ?: super.onBackPressed()
                }
            } else {
                super.onBackPressed()
            }
        }
    }
}