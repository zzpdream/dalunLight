package com.zz.led.cmd.client2server.heart;


import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;
import com.zz.led.protocol.client2server.C2SMCode;
import com.zz.led.protocol.client2server.C2SSMCode;
import com.zz.led.utils.ParseUtils;

import timber.log.Timber;

/**
 * Created by munin on 2017/12/14.
 * 心跳
 */

public class HeartCmd {
    //   心跳包
    public static final byte[] heart() {
        byte[] CID = C2SCIDCode.M5003.getBytes();
        /**
         * 计算CommandLength的长度：报文中Message ID以及Text Message字段,包括CL本身的总字节数
         */
        int cl = CID.length + C2SProtocol.CL_LENGTH;

        byte[] CL = ParseUtils.little_intToByte(cl,2);
        byte[] SID = {0x00,0x00,0x00,0x00};
        int tl = C2SProtocol.TL_LENGTH + C2SMCode.M.length+ C2SSMCode.SOURCE_MAC.length+CL.length+CID.length+SID.length+C2SProtocol.CK_LENGTH;
        byte[] TL = ParseUtils.little_intToByte(tl,4);

        byte[] total_data = ParseUtils.byteMerger(TL,C2SMCode.M,C2SSMCode.SOURCE_MAC,CL,CID,SID);
        byte[] CK = C2SProtocol.getCheckNum(total_data);
        Timber.d("MH_H:"+"CID: "+ ParseUtils.parseByte2HexStr(CID)
                +" CL: "+ParseUtils.parseByte2HexStr(CID)+" SID: "+SID
                +" TL: "+ParseUtils.parseByte2HexStr(TL) +" total_data: "+ParseUtils.parseByte2HexStr(total_data)
                +" CK: "+ParseUtils.parseByte2HexStr(CK));
        ParseUtils.byteTo16(ParseUtils.byteMerger(total_data,CK));

        return ParseUtils.byteMerger(total_data,CK);
    }
}
