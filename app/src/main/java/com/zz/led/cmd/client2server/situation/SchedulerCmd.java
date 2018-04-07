package com.zz.led.cmd.client2server.situation;


import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;

/**
 * Created by munin on 2017/12/14.
 * 定时器相关指令
 */

public class SchedulerCmd {
    //  添加定时器
    public static final byte[] add(String content) {
        byte[] CID = C2SCIDCode.M5067.getBytes();
        byte[] INFO = content.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //    删除定时器
    public static final byte[] del(String content) {
        byte[] CID = C2SCIDCode.M5071.getBytes();
        byte[] INFO = content.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //    需要改定时器
    public static final byte[] modify(String content) {
        byte[] CID = C2SCIDCode.M5069.getBytes();
        byte[] INFO = content.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //     获取定时器
    public static final byte[] getScheduler() {
        byte[] CID = C2SCIDCode.M5065.getBytes();
        return C2SProtocol.getByteNoText(CID, C2SProtocol.ZERO_SID);
    }
}
