package com.example.administrator.duotu;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Fragment3 extends Fragment {


    private Button tuisong;
    private EditText tuisongneirong;
    private EditText tuisongbiaoti;
    String strChannelID = "strChannelID1";
    private NotificationManager manager;
    private NotificationChannel channel;
    String TAG="fr03111111111";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment3, container, false);
        tuisong = inflate.findViewById(R.id.tuisong);
        tuisongneirong = inflate.findViewById(R.id.tuisongneirong);
        tuisongbiaoti = inflate.findViewById(R.id.tuisongbiaoti);
        return inflate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Date date = new Date(System.currentTimeMillis());  //系统当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        Log.i(TAG, "onClick: 1111111111"+format);
/*      manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        channel = new NotificationChannel(strChannelID, "不知道这个是啥", NotificationManager.IMPORTANCE_DEFAULT);

        manager.createNotificationChannel(channel);*/

        tuisong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { 
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Notification.Builder builder = new Notification.Builder(getActivity(), strChannelID);
                    builder.setSmallIcon(R.drawable.ic_launcher_background);
                    builder.setTicker("这个Ticker是通知的标题吗？");
                    builder.setContentTitle("这个是ContentTitle:" + tuisongbiaoti.getText().toString());
                    builder.setContentText("这个是ContentText:" + tuisongneirong.getText().toString());
                    manager.notify(0x01, builder.build());
                    Log.i(TAG, "onClick: 1111111111");
                } else {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
                    builder.setSmallIcon(R.drawable.ic_launcher_background);
                    builder.setContentTitle(tuisongbiaoti.getText().toString());
                    builder.setContentText(tuisongneirong.getText().toString());
                    builder.setOngoing(true);
                    manager.notify(0x02, builder.build());
                    Log.i(TAG, "onClick: 2222222222");
                }
            }
        });
    }
}
