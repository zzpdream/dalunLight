package com.zz.led.cmd.client2server.situation;


import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;

/**
 * Created by munin on 2017/12/14.
 * 情景相关指令
 */

public class SituationCmd {
    //    添加情景
    public static final byte[] add(String name, String content) {
        byte[] CID = C2SCIDCode.M5057.getBytes();
        if (content.endsWith("#"))
            content = content.substring(0, content.length() - 1);
        byte[] INFO = (name + "|" + content).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID, INFO, SID);
    }

    //    删除情景
    public static final byte[] del(String content) {
        byte[] CID = C2SCIDCode.M5061.getBytes();
        byte[] INFO = content.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID, INFO, SID);
    }

    //    执行情景
    public static final byte[] excute(String content) {
        byte[] CID = C2SCIDCode.M5063.getBytes();
        byte[] INFO = content.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //     修改情景
    public static final byte[] modify(String name, String id, String content) {
        byte[] CID = C2SCIDCode.M5059.getBytes();
        byte[] INFO = (name + "|" + id + "#" + content).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID, INFO, SID);
    }

    //     获取情景
    public static final byte[] getSituation() {
        byte[] CID = C2SCIDCode.M5055.getBytes();
        return C2SProtocol.getByteNoText(CID, C2SProtocol.ZERO_SID);
    }

    //      情景测试
    public static final byte[] test(String name, String content) {
        byte[] CID = C2SCIDCode.M5087.getBytes();
        if (content.endsWith("#"))
            content = content.substring(0, content.length() - 1);
        byte[] INFO = (name + "|" + content).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID, INFO, SID);
    }
}
