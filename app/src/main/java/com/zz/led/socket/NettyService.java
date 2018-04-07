package com.zz.led.socket;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.zz.led.cmd.client2server.heart.HeartCmd;
import com.zz.led.utils.ByteUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import timber.log.Timber;

/**
 *
 * Created by LiuSaibao on 11/17/2016.
 */
public class NettyService extends Service implements NettyListener {

    private NetworkReceiver receiver;
    private static String sessionId = null;

    private ScheduledExecutorService mScheduledExecutorService;
    private void shutdown() {
        if (mScheduledExecutorService != null) {
            mScheduledExecutorService.shutdown();
            mScheduledExecutorService = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        receiver = new NetworkReceiver();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

        // 自定义心跳，每隔20秒向服务器发送心跳包
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                byte[] requestBody = HeartCmd.heart();
                NettyClient.getInstance().sendMsgToServer(requestBody, new ChannelFutureListener() {    //3
                    @Override
                    public void operationComplete(ChannelFuture future) {
                        if (future.isSuccess()) {                //4
                            Timber.e("Write heartbeat successful");

                        } else {
                            Timber.e("Write heartbeat error");
                        }
                    }
                });
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NettyClient.getInstance().setListener(this);
        connect();
        return START_NOT_STICKY;
    }

    @Override
    public void onServiceStatusConnectChanged(int statusCode) {		//连接状态监听
        Timber.e("connect status:%d", statusCode);
        if (statusCode == NettyListener.STATUS_CONNECT_SUCCESS) {
            authenticData();
        } else {
            Timber.e("tcp connect error");
        }
    }

    /**
     * 认证数据请求
     */
    private void authenticData() {
    }

    @Override
    public void onMessageResponse(ByteBuf byteBuf) {
        byte[] bytes = byteBuf.array();
        Timber.e("tcp receive data:%s", ByteUtil.bytesToHex(bytes));
    }

    private void handle(int t, int i, int f) {
        // TODO 实现自己的业务逻辑
    }

    private void connect(){
        if (!NettyClient.getInstance().getConnectStatus()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NettyClient.getInstance().connect();//连接服务器
                }
            }).start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        shutdown();
        NettyClient.getInstance().setReconnectNum(0);
        NettyClient.getInstance().disconnect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
                        || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    connect();
                }
            }
        }
    }
}
