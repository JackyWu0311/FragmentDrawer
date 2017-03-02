package ntust.csie.mime_app_pager;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by user on 2016/11/22.
 */
public class VideoFragment extends Fragment{
    VideoView vvPlayer = null;
    Button btnVideo;


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        btnVideo = (Button)getView().findViewById(R.id.btnVideo);
        vvPlayer = (VideoView)getView().findViewById(R.id.vvPlayer);
        btnVideo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0){
                playVideo(R.raw.wildlife);
            }
        });
    }

    public void playVideo(int videoId){
        vvPlayer.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + videoId));
        vvPlayer.setMediaController(new MediaController(getActivity()));
        vvPlayer.requestFocus();
        vvPlayer.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle saveInstanceState){
        return inflater.inflate(R.layout.video , container , false);
    }






















}
