package com.example.seiber.alexapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Seiber on 18/12/2016.
 */


public class PantallaInicio extends AppCompatActivity {
    private ImageView miImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent intent = getIntent();

        miImageView = (ImageView) findViewById(R.id.imageView);

        /*Clase interna*/
        class MyThread extends Thread {
            public void run ( ) {
                final Bitmap b = loadImageFromNetwork("http://images4.fanpop.com/image/answers/1888000/1888104_1313506728084.28res_375_300.jpg");
                miImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        miImageView.setImageBitmap(b);
                    }
                });
            }
        }

        /*Al clicar sobre el boton descargara la imagen de internet y la pondra en el image view*/
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread t = new MyThread();
                t.start();
            }
        });
    }

    private Bitmap loadImageFromNetwork(String s) {
        Bitmap bmp = null;
        try {
            URL url = new URL(s);   //convertimos el string a url
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());    //conectamos a la imagen de internet y la creamos como bmp
            return bmp;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bmp;
    }
}
