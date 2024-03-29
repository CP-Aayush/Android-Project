package com.example.aayush.downloadimageapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImg;


    public void downloadImage(View view)
    {

        //https://www.google.co.in/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi98puY-szeAhXDfysKHQ6pDEcQjRx6BAgBEAU&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FPeter_Griffin&psig=AOvVaw2cX8f5dj_2Jx8oB6v8PAXA&ust=1542046811443078

        ImageDownloader task=new ImageDownloader();

        Bitmap myImage;

        try {
            myImage=task.execute("https://upload.wikimedia.org/wikipedia/en/c/c2/Peter_Griffin.png").get();
            downloadedImg.setImageBitmap(myImage);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("Interaction","Button Tapped");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImg =(ImageView) findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url= new URL(urls[0]);

                HttpURLConnection connection=(HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream=connection.getInputStream();

                Bitmap myBitmap=BitmapFactory.decodeStream(inputStream);

                return myBitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
        }

    }

}
