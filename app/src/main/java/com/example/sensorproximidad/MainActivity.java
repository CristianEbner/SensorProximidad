package com.example.sensorproximidad;

import static java.lang.String.*;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

//la implementacion sensoreventlistener significa que debe proporcionar la implementacion de SensorCahnger y onAccuracyChanged

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //se configura la interfaz de usuarios cargando el diseño definido en el activity_main.xml

    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;
    TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se inicializan las variables

        ln = findViewById(R.id.activity_main);//un objeto linearlayout que representa el diseño principal de la actividad
        tv = findViewById(R.id.tv);//un objeto textview que se utilizara para mostrar  el valor del sensor
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);//un objeto  Sensormanager que se utiliza  para gestionar los sensores  del dispositivo
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);//se obtiene el sensor de proximidad con sensor,type_proximity del dispositivo
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);//se registra la clase this  como oyente del sensor de proximidad
    }

    @Override
    //se actualiza el textview o tv con el valor actual del sensor de proximidad
    public void onSensorChanged(SensorEvent event) {
        //el valor del sensor se encuentra en event.values[0]
        String text = valueOf(event.values[0]);
        tv.setText(text);//luego se convierte el valor en una cadena y muestra en el textview
        float valor = Float.parseFloat(text);//se analiza el valor del sensor de proximidad
        if (valor==0){//si el valor es igual a 0 el objeto esta cerca del sensor
            ln.setBackgroundColor(Color.GREEN);//el fondo de pantalla se cambiara a verde
        }else {
            ln.setBackgroundColor(Color.RED);// de lo contrario se cambiara a rojo
        }
    }

    @Override
    //no realiza ninguna accion en este caso
    public void onAccuracyChanged(Sensor sensor, int i){

    }
}