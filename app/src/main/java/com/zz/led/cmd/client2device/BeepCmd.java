package com.zz.led.cmd.client2device;


import com.zz.led.protocol.C2PProtocol;
import com.zz.led.protocol.client2device.C2PCIDCode;
import com.zz.led.utils.ParseUtils;

/**
 * Created by munin on 2017/12/14.
 * 暂时废弃
 */

public class BeepCmd {

    public static final byte[] beep(boolean beep){
        byte[] CID = C2PCIDCode.M5032.getBytes();
        String beepdata = "0";
        if(beep)
            beepdata = "1";
        byte[] USERINFO = beepdata.getBytes();
        byte[] SID = ParseUtils.little_intToByte(0,4);
        return C2PProtocol.getLightByteWithText(CID,USERINFO,SID);
    }
}
