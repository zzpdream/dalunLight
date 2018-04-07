package com.zz.led.protocol;

import com.zz.led.protocol.client2server.C2SMCode;
import com.zz.led.protocol.client2server.C2SSMCode;
import com.zz.led.utils.ParseUtils;

/**
 * Created by Administrator on 2017/11/1.
 */

public class C2SProtocol {
    /*** Telegram Length自身的长度，协议规定为4字节,Commond Length自身的长度,CheckNum自身的长度*/
    public static final int TL_LENGTH = 4, CL_LENGTH = 2, CID_LENGTH = 4, CK_LENGTH = 1, SID_LENGTH = 4;
    public static final int CL_BASE_LENGTH = CL_LENGTH + CID_LENGTH;
    public static final int TL_BASE_LENGTH = TL_LENGTH + C2SMCode.M.length + C2SSMCode.SOURCE_MAC.length + CL_LENGTH + CID_LENGTH + SID_LENGTH + CK_LENGTH;
    public static int BASE_SID = 0;
    public static final byte[] ZERO_SID = {0x00, 0x00, 0x00, 0x00};


    /**
     * 拼接无text的byte数组
     *
     * @param CID
     * @param SID
     * @return
     */
    public static byte[] getByteNoText(byte[] CID, byte[] SID) {
        int cl = CL_BASE_LENGTH;
        byte[] CL = ParseUtils.little_intToByte(cl, 2);
        int tl = TL_BASE_LENGTH;
        byte[] TL = ParseUtils.little_intToByte(tl, 4);
        byte[] total_data = ParseUtils.byteMerger(TL, C2SMCode.M, C2SSMCode.SOURCE_MAC, CL, CID, SID);
        byte[] CK = getCheckNum(total_data);
        return ParseUtils.byteMerger(total_data, CK);
    }

    public static byte[] getSID() {
        BASE_SID += 1;
        byte[] SID = ParseUtils.little_intToByte(BASE_SID, 4);
        return SID;
    }

    /**
     * 通过依次异或运算，得到checknum
     *
     * @param data
     * @return
     */
    public static byte[] getCheckNum(byte[] data) {
        int checknum = data[0] ^ data[1];
        for (int i = 2; i < data.length; i++) {
            checknum = checknum ^ data[i];
        }
        byte[] CK = ParseUtils.little_intToByte(checknum, 1);
        return CK;
    }

    /**
     * 拼接有text的byte数组
     *
     * @param CID
     * @param MACADDRESS
     * @param BC
     * @param SID
     * @return
     */
    public static byte[] getByteWithBC(byte[] CID, byte[] MACADDRESS, byte[] BC, byte[] SID) {
        int cl = CL_BASE_LENGTH + MACADDRESS.length;
        byte[] CL = ParseUtils.little_intToByte(cl, 2);
        int tl = TL_BASE_LENGTH + MACADDRESS.length + BC.length;
        byte[] TL = ParseUtils.little_intToByte(tl, 4);
        byte[] total_data = ParseUtils.byteMerger(TL, C2SMCode.M, C2SSMCode.SOURCE_MAC, CL, CID, MACADDRESS, BC, SID);
        byte[] CK = getCheckNum(total_data);
        return ParseUtils.byteMerger(total_data, CK);
    }

    /**
     * 拼接有text的byte数组
     *
     * @param CID
     * @param TEXT
     * @param SID
     * @return
     */
    public static byte[] getByteWithText(byte[] CID, byte[] TEXT, byte[] SID) {
        int cl = CL_BASE_LENGTH + TEXT.length;
        byte[] CL = ParseUtils.little_intToByte(cl, 2);
        int tl = TL_BASE_LENGTH + TEXT.length;
        byte[] TL = ParseUtils.little_intToByte(tl, 4);
        byte[] total_data = ParseUtils.byteMerger(TL, C2SMCode.M, C2SSMCode.SOURCE_MAC, CL, CID, TEXT, SID);
        byte[] CK = getCheckNum(total_data);
        return ParseUtils.byteMerger(total_data, CK);
    }


















}
