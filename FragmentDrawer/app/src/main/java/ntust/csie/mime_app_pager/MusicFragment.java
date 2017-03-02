package ntust.csie.mime_app_pager;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by user on 2016/11/22.
 */



public class MusicFragment extends Fragment {
    Button btnAudio , btnAudioStop;
    TextView txtLyrics;
    ImageView ivSinger;
    MediaPlayer mp = null;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        btnAudio = (Button)getView().findViewById(R.id.btnAudio);
        btnAudioStop = (Button)getView().findViewById(R.id.btnAudioStop);
        txtLyrics = (TextView)getView().findViewById(R.id.txtLyrics);
        ivSinger = (ImageView)getView().findViewById(R.id.ivSinger);


        btnAudio.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0){
                playAudio(R.raw.aguyisguy);
                showImage(R.drawable.doris);
                showLyrics(R.raw.lyrics);
            }
        });
        btnAudioStop.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if(mp != null){
                    mp.release();
                    mp=null;
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        return inflater.inflate(R.layout.music, container,false);
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    private void showLyrics(int lyricsId){
        InputStream lyrisFile = getResources().openRawResource(lyricsId);
        String lyrics = "" , line;

        try{
            BufferedReader bf =  new BufferedReader(new InputStreamReader(lyrisFile));
            while((line = bf.readLine())!= null){
                lyrics = line + "\n";
            }
            bf.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        txtLyrics.setText(lyrics);

    }

    private void showImage(int imageId){
        ivSinger.setImageURI(Uri.parse("android.resource://" + getActivity().getPackageName()+ "/" + imageId));
    }

    public void playAudio(int songId){
        mp = new MediaPlayer();
        mp = MediaPlayer.create(getActivity().getApplicationContext(), songId);
        mp.start();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mp!= null){
            mp.stop();
            mp.release();
        }
    }










}
