package com.showtyclef.androidlifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private var number_state = 0
const val TAG = "Main Activity"
const val NUMBER = "number_scale_key"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        if (savedInstanceState !=null){
            number_state = savedInstanceState.getInt(NUMBER, 0)
        }
        setContentView(R.layout.activity_main)

        val initialTextviewTranslationY =
            number_scale.translationY

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                number_scale.text = progress.toString()

                val translationDistance = (initialTextviewTranslationY +
                        progress * resources.getDimension(R.dimen.text_anim_step) * -1)

                number_scale.animate().translationY(translationDistance)

                if (!fromUser)
                    number_scale.animate().setDuration(700).rotationBy(360f)
                        .translationY(initialTextviewTranslationY)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        button_reset.setOnClickListener { v ->
            seekBar.progress = 0
        }

        shareButton.setOnClickListener{
            val message:String = number_scale.text.toString()

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share to:"))
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NUMBER, number_state)
        Log.d(TAG, "onSaveInstance called")
    }

}



