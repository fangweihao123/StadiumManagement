package com.example.po.stadiummanagement3.Fragment;

/**
 * Created by 13701 on 2017/11/25.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.po.stadiummanagement3.Activity.LoginActivity;
import com.example.po.stadiummanagement3.Activity.MainActivity;
import com.example.po.stadiummanagement3.R;
import com.example.po.stadiummanagement3.WebService.HttpService;
import com.example.po.stadiummanagement3.step.config.Constant;
import com.example.po.stadiummanagement3.step.service.StepService;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick ;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 13701 on 2017/11/23.
 */

public class LoadingFragment extends Fragment implements Handler.Callback{

    private long TIME_INTERVAL = 500;
    private TextView mStep;
    private Handler delayHandler;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));

    @BindView(R.id.background)
    ImageView imageView;
    private final String picUrl = "http://guolin.tech/api/bing_pic";
    @OnClick(R.id.background)
    void onClick(View view){
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                //更新步数
                mStep.setText(msg.getData().getInt("step") + "");
                delayHandler.sendEmptyMessageDelayed(Constant.REQUEST_SERVER, TIME_INTERVAL);
                break;
            case Constant.REQUEST_SERVER:
                try {
                    Message msgl = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                    msgl.replyTo = mGetReplyMessenger;
                    messenger.send(msgl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
        return false;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.loading_fragment,container,false);
        //imageView = v.findViewById(R.id.background);
        mStep = v.findViewById(R.id.step_info);
        delayHandler = new Handler(this);    //初始化handler
        loadPic();
        ButterKnife.bind(this,v);
        return v;
    }

    private void loadPic(){
        HttpService.sendOkHttpRequest(picUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try{
                    final String bingPic = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getActivity()).load(bingPic).into(imageView);
                        }
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        setupService();   //在onStart()中开启服务，使程序即使退到后台再到前台也能开启服务
    }

    /**
     * 开启服务
     */
    public void setupService() {
        Intent intent = new Intent(getContext(), StepService.class);
        getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
    }




}