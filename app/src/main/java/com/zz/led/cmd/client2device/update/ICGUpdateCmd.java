package com.zz.led.cmd.client2device.update;


import com.zz.led.protocol.C2PProtocol;
import com.zz.led.protocol.client2device.C2PCIDCode;
import com.zz.led.utils.ParseUtils;

/**
 * Created by munin on 2017/12/14.
 * 灯具更新
 */

public class ICGUpdateCmd {

    public static final byte[] updateICG(String mac_address, String page_num, byte[] crc, byte[] bc) {
        byte[] CID = C2PCIDCode.M5024.getBytes();
        byte[] text_byte = (mac_address+page_num).getBytes();
        byte[] SID = ParseUtils.little_intToByte(Integer.valueOf(page_num)+8,4);
        byte[] result = C2PProtocol.getByteWithBC(CID,text_byte,crc,bc,SID);
        return result;
    }

    public static final byte[] updateEnd(String mac_address, byte[] crc, int totalSize) {
        byte[] CID = C2PCIDCode.M5026.getBytes();
        byte[] ADDRESS = mac_address.getBytes();
        byte[] TEXT = new byte[ADDRESS.length + 2];
        System.arraycopy(ADDRESS, 0, TEXT, 0, ADDRESS.length);
        System.arraycopy(crc, 0, TEXT, ADDRESS.length, 2);
        byte[] SID = ParseUtils.little_intToByte(totalSize + 20, 4);
        return C2PProtocol.getLightByteWithText(CID, TEXT, SID);
    }

    public static final byte[] updateStart(String mac_address) {
        byte[] CID = C2PCIDCode.M5022.getBytes();
        byte[] USERINFO = mac_address.getBytes();
        byte[] SID = ParseUtils.little_intToByte(0,4);
        return C2PProtocol.getLightByteWithText(CID,USERINFO,SID);
    }


}
