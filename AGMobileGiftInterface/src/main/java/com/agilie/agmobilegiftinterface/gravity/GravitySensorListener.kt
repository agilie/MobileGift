package com.agilie.agmobilegiftinterface.gravity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class GravitySensorListener(context: Context) : SensorEventListener {


    interface onGravityListener {
        fun onGravity(x: Float, y: Float)
    }

    var gravityListener: onGravityListener? = null

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            gravityListener?.onGravity(event.values[0], event.values[1])
        }
    }

    fun onStopSensor() {
        sensorManager.unregisterListener(this)
    }

    fun onResumeSensor() {
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL)
    }
}