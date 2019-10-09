package com.jaluma.jardinBotanico.ui.regadera

import android.app.Activity
import android.graphics.drawable.AnimatedImageDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.jaluma.jardinBotanico.R
import kotlin.math.acos
import kotlin.math.roundToInt
import kotlin.math.sqrt


class RegaderaFragment : Fragment(), SensorEventListener {

    private var lastState: Boolean? = false
    lateinit var frameAnimation: AnimatedImageDrawable
    private lateinit var mSensorManager: SensorManager
    private lateinit var senAccelerometer: Sensor

    private lateinit var layout: View
    private var mediaPlayer: MediaPlayer? = null

    private var lastUpdate: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layout = inflater.inflate(R.layout.fragment_regadera, container, false)

        val img = layout.findViewById<ImageView>(R.id.regadera)
        img.setImageDrawable(resources.getDrawable(R.drawable.regadera))

        frameAnimation = img.drawable as AnimatedImageDrawable
        mSensorManager = activity?.getSystemService(Activity.SENSOR_SERVICE) as SensorManager

        senAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        mSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        return layout
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // nothing to do
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {

            val curTime = System.currentTimeMillis()
            val diffTime = (curTime - lastUpdate)

            if (diffTime > 1000) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val norm = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

                // Normalize the accelerometer vector
                val xNormalizer = (x / norm)
                val inclination = Math.toDegrees(acos(xNormalizer.toDouble())).roundToInt()

                if (inclination in 11..69) {
                    if (!frameAnimation.isRunning) {
                        mediaPlayer = MediaPlayer.create(layout.context, R.raw.ducha)
                        mediaPlayer?.start()
                        frameAnimation.start()
                    }

                    lastUpdate = curTime
                } else {
                    frameAnimation.stop()
                    mediaPlayer?.stop()
                }
            }


        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        if (!lastState!!) {
            mediaPlayer?.start()
        }
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)

        // si se quita el primer not null crashea...
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer?.stop()
        }
    }
}