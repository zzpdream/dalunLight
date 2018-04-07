package com.zz.led.protocol;


import com.zz.led.protocol.client2device.C2PGCode;
import com.zz.led.protocol.client2device.C2PSMCode;
import com.zz.led.protocol.client2server.C2SMCode;
import com.zz.led.protocol.client2server.C2SSMCode;
import com.zz.led.utils.ParseUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/11/1.
 */

public class C2PProtocol {
    /*** Telegram Length自身的长度，协议规定为4字节,Commond Length自身的长度,CheckNum自身的长度*/
    public static final int TL_LENGTH = 4, CL_LENGTH = 2, CID_LENGTH = 4, CK_LENGTH = 1, SID_LENGTH = 4;
    public static final int CL_BASE_LENGTH = CL_LENGTH + CID_LENGTH;
    public static final int TL_BASE_LENGTH = TL_LENGTH + C2SMCode.M.length + C2SSMCode.SOURCE_MAC.length + CL_LENGTH + CID_LENGTH + SID_LENGTH + CK_LENGTH;
    public static int BASE_SID = 0;
    private static byte[] PARSE_TEXT;
    public  static final byte[] ZERO_SID = {0x00,0x00,0x00,0x00};
    /**
     * 拼接有text的byte数组
     * @param CID
     * @param TEXT
     * @param SID
     * @return
     */
    public static byte[] getLightByteWithText(byte[] CID,byte[] TEXT,byte[] SID){
        int cl = CL_BASE_LENGTH+ TEXT.length;
        byte[] CL = ParseUtils.little_intToByte(cl,2);
        int tl = TL_BASE_LENGTH + TEXT.length;
        byte[] TL = ParseUtils.little_intToByte(tl,4);
        byte[] total_data = ParseUtils.byteMerger(TL, C2PGCode.G, C2PSMCode.SOURCE_MAC,CL,CID,TEXT,SID);
        byte[] CK = getCheckNum(total_data);
        return ParseUtils.byteMerger(total_data,CK);
    }

    public static byte[] getByteWithBC(byte[] CID,byte[] TEXT,byte[] CRC, byte[] BC,byte[] SID){
        int cl = CL_BASE_LENGTH+ TEXT.length + CRC.length;
        byte[] CL = ParseUtils.little_intToByte(cl,2);
        int tl = TL_BASE_LENGTH + TEXT.length+CRC.length+BC.length;
        byte[] TL = ParseUtils.little_intToByte(tl,4);
        byte[] total_data = ParseUtils.byteMerger(TL,C2PGCode.G,C2PSMCode.SOURCE_MAC,CL,CID,TEXT,CRC,BC,SID);
        byte[] CK = getCheckNum(total_data);
        return ParseUtils.byteMerger(total_data,CK);
    }

    /**
     * 通过依次异或运算，得到checknum
     * @param data
     * @return
     */
    public static byte[] getCheckNum(byte[] data){
        int checknum = data[0] ^ data[1];
        for(int i=2;i<data.length;i++){
            checknum = checknum ^ data[i];
        }
        byte[] CK = ParseUtils.little_intToByte(checknum,1);
        return CK;
    }

    public static String parseConnectServier(byte[] msg){
        if(checkHasTextData(msg)) {
            try{
                String result = new String(PARSE_TEXT,"UTF-8");
                if(result.length()>0){
                    return result;
                } else
                    return "N";

            }catch (UnsupportedEncodingException e){
                return  "N";
            }
        } else {
            return "N";
        }
    }

    public static boolean checkHasTextData(byte[] msg){
        byte[] TL = {msg[0],msg[1],msg[2],msg[3]};
        int tl = ParseUtils.little_bytesToInt(TL);
        byte[] M = {msg[4]};
        byte[] SOURCEMAC = {msg[5],msg[6],msg[7],msg[8],msg[9],msg[10]};
        byte[] CL = {msg[11],msg[12]};
        byte[] CID = {msg[13],msg[14],msg[15],msg[16]};
        int textlength = tl-TL_BASE_LENGTH;
        PARSE_TEXT = new byte[textlength];
        for(int i=17 ; i<17+textlength;i++){
            PARSE_TEXT[i-17] = msg[i];
        }
        byte[] SID = {msg[17+textlength],msg[18+textlength],msg[19+textlength],msg[20+textlength]};
        byte[] CK = {msg[21+textlength]};
        byte[] total_data = ParseUtils.byteMerger(TL,M,SOURCEMAC,CL,CID,PARSE_TEXT,SID,CK);
        if(ParseUtils.little_bytesToInt(getCheckNum(total_data))!=0){
//            Log.d("校验错误","校验错误");
            return false;
        } else{
//            Log.d("校验正确","校验正确");
            return true;
        }
    }

    public static String parseUpdate(byte[] msg){
        if(checkHasTextData(msg)) {
            try{
                String result = new String(PARSE_TEXT,"UTF-8");
                if(result.length()>0){
                    return result;
                } else
                    return null;

            }catch (UnsupportedEncodingException e){
                return  null;
            }
        } else {
            return null;
        }
    }



}
