package com.zz.led.cmd.client2server.light;


import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;
import com.zz.led.protocol.client2server.C2SSIDCode;

import timber.log.Timber;

/**
 * Created by munin on 2017/12/14.
 * icg相关指令
 */

public class ICGCmd {
    //    改变灯(具体控制色温和亮度)
    public static final byte[] changeLight(String address, int strength, int ct_scale, String rgb, String isFinal) {
        byte[] CID = C2SCIDCode.M5009.getBytes();
        byte[] INFO = (address+"$"+ String.format("%02d", strength)+ String.format("%02d", ct_scale)+rgb+isFinal).getBytes();
        Timber.e("SHENME"+""+address+"$"+ String.format("%02d", strength)+ String.format("%02d", ct_scale)+rgb+isFinal);
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //    改变灯的模式
    public static final byte[] changeMode(String address, String mode) {
        if(address.endsWith("|"))
            address = address.substring(0,address.length()-1);
//        Log.e("gxm控制的灯具模式",address);
        byte[] CID = C2SCIDCode.M5013.getBytes();
        byte[] INFO = (address+"$"+mode).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //    获取所有的icg状态
    public static final byte[] online(String uid) {
        byte[] CID = C2SCIDCode.M5077.getBytes();
        byte[] TEXT = uid.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,TEXT,SID);
    }

    //     查询灯状态
    public static final byte[] lightState(String mac_address) {
        byte[] CID = C2SCIDCode.M5005.getBytes();
        byte[] ADDRESS = mac_address.getBytes();
        byte[] SID = {0x00,0x00,0x00,0x00};
        return C2SProtocol.getByteWithText(CID,ADDRESS,SID);
    }

    //     查询灯的版本
    public static final byte[] lightVersion(String lightType) {
        if(lightType.endsWith("|"))
            lightType = lightType.substring(0,lightType.length()-1);
        byte[] CID = C2SCIDCode.M5081.getBytes();
        byte[] INFO = lightType.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //     查询icg的mac地址
    public static final byte[] lightAddress(String ssid, String netPwd) {
        byte[] CID = C2SCIDCode.M5045.getBytes();
        byte[] TEXTINFO = (ssid + "|" +netPwd).getBytes();
        return C2SProtocol.getByteWithText(CID,TEXTINFO, C2SSIDCode.SID);
    }

    //     icg开关状态
    public static final byte[] openCloseICG(String mac_address, String status) {
        if(mac_address.endsWith("|"))
            mac_address = mac_address.substring(0,mac_address.length()-1);
//        Log.e("gxm控制的灯具开关",address);
        byte[] CID = C2SCIDCode.M5007.getBytes();
        byte[] INFO = (mac_address+"$"+status).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }


    //   联网提示音
    public static final byte[] beep(String mac_address) {
        byte[] CID = C2SCIDCode.M5083.getBytes();
        byte[] ADDRESS = mac_address.getBytes();
        byte[] SID = {0x00,0x00,0x00,0x00};
        return C2SProtocol.getByteWithText(CID,ADDRESS,SID);
    }
}
