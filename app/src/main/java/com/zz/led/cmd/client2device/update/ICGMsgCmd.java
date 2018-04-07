package com.zz.led.cmd.client2device.update;


import com.zz.led.mvp.model.Api.Api;
import com.zz.led.protocol.C2PProtocol;
import com.zz.led.protocol.client2device.C2PCIDCode;

/**
 * Created by munin on 2017/12/14.
 * 让灯去绑定服务器地址
 */

public class ICGMsgCmd {

    public static final byte[] lightMsg() {
        byte[] CID = C2PCIDCode.M5034.getBytes();
        byte[] USERINFO = Api.IP.getBytes();
        byte[] SID = {0x00,0x00,0x00,0x00};
        return C2PProtocol.getLightByteWithText(CID,USERINFO,SID);
    }
}
