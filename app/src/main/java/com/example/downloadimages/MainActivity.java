package com.example.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageview;

    Button button;

    public void downloadImage(View view){
        ImageDownloader task=new ImageDownloader();
        Bitmap myImage;

        try{
            myImage=task.execute("https://static.billboard.com/files/media/chadwick-boseman-march-2018-billboard-1548-compressed.jpg").get();
            imageview.setImageBitmap(myImage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview=findViewById(R.id.imageView);

        button=findViewById(R.id.downloadImageButton);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url=new URL(urls[0]);
                HttpsURLConnection connection=(HttpsURLConnection) url.openConnection();
                connection.connect();
                InputStream in=connection.getInputStream();
                Bitmap myBitMap= BitmapFactory.decodeStream(in);
                return myBitMap;


            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
}