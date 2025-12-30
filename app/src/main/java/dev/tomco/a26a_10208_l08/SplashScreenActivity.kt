package dev.tomco.a26a_10208_l08

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import dev.tomco.a26a_10208_l08.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startAnimation(binding.splashLOTTIELottie)
    }

    private fun transactToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun startAnimation(lottieAnimationView: LottieAnimationView) {
        lottieAnimationView.resumeAnimation()

        lottieAnimationView.addAnimatorListener(
            object: Animator.AnimatorListener{
                override fun onAnimationCancel(p0: Animator) {
                    // Remove all data created and clean memory.
                }

                override fun onAnimationEnd(p0: Animator) {
                    transactToMainActivity()
                }

                override fun onAnimationRepeat(p0: Animator) {
                    // check if data received
                    // if true - stop repeating
                }

                override fun onAnimationStart(p0: Animator) {
                    // go fetch data and apply callback when done.
                }

            }
        )


    }
}