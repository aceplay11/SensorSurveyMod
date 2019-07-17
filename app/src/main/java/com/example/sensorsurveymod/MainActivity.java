package com.example.sensorsurveymod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor sensorProximity;
    private Sensor sensorLight;

    private TextView tvLight;
    private TextView tvProximity;

    private SensorManager sensorManager;
    StringBuilder sensorText = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLight = findViewById(R.id.label_light);
        tvProximity = findViewById(R.id.label_proximity);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        String sensor_error = getResources().getString(R.string.eror_no_sensor);

        if (sensorLight == null) {
            tvLight.setText(sensor_error);
        } else {

        }

        if (sensorProximity == null) {
            tvProximity.setText(sensor_error);
        } else {

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sensorLight != null) {
            sensorManager.registerListener(this, sensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorProximity != null) {
            sensorManager.registerListener(this, sensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                tvLight.setText(getResources().getString(R.string.label_light, currentValue));
                break;

            case Sensor.TYPE_PROXIMITY:
                tvProximity.setText(getResources().getString(R.string.label_proximity, currentValue));
                break;
                default:
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}


