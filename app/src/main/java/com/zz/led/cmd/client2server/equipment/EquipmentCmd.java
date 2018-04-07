package com.zz.led.cmd.client2server.equipment;


import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;
import com.zz.led.utils.ParseUtils;

/**
 * Created by munin on 2017/12/14.
 * 家电相关指令
 */

public class EquipmentCmd {
    //控制红外设备的指令
    public static final byte[] controlEquipment(String mac_address, String bc_code) {
        byte[] CID = C2SCIDCode.M5033.getBytes();
        byte[] address_byte = mac_address.getBytes();
        byte[] bc = ParseUtils.tvhexStringToByte(bc_code);
        byte[] SID = C2SProtocol.getSID();
        byte[] result = C2SProtocol.getByteWithBC(CID,address_byte,bc,SID);
        return result;
    }

    //    开始红外学习指令
    public static final byte[] startInfraredLearn(String mac_address) {
        byte[] CID = C2SCIDCode.M5027.getBytes();
        byte[] INFO = (mac_address).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //    停止红外学习指令
    public static final byte[] cancelInfraredLearn(String mac_address) {
        byte[] CID = C2SCIDCode.M5031.getBytes();
        byte[] INFO = (mac_address).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //     学习红外
    public static final byte[] infraredLearn(String mac_address) {
        byte[] CID = C2SCIDCode.M5029.getBytes();
        byte[] INFO = (mac_address).getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }

    //     空调控制
    public static final byte[] airConditionControl(String address, String bc_code) {
        byte[] CID = C2SCIDCode.M5033.getBytes();
        byte[] address_byte = address.getBytes();
        byte[] bc = ParseUtils.hexStringToByte(bc_code);
        byte[] SID = C2SProtocol.getSID();
        byte[] result = C2SProtocol.getByteWithBC(CID,address_byte,bc,SID);
        return  result;
    }
}
