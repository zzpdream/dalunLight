package com.zz.led.cmd.client2server.log;

import android.app.Activity;

import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;


/**
 * Created by munin on 2017/12/14.
 */

public class LogCmd {
    //OTA 日志
    public static final byte[] log(String mac_address, String logType, String logContent, Activity activity) {
        byte[] CID = C2SCIDCode.M5085.getBytes();
        String text = mac_address + "|" + logType +"|" + logContent
                + ";<br />--------------------------------------------------------<br />";
        text.replace("=","").replace("'","").replace("net user","").replace("xp_cmdshell","").replace("/add","").replace("exec master.dbo.xp_cmdshell","")
                .replace("net localgroup administrators","").replace("select","").replace("count","").replace("Asc","").replace("char","").replace("mid","")
                .replace(":","").replace("\\","").replace("insert","").replace("delete from","").replace("drop table","").replace("update","").replace("truncate","")
                .replace("from","").replace("%","");
        byte[] INFO = text.getBytes();
        byte[] SID = C2SProtocol.getSID();
        return C2SProtocol.getByteWithText(CID,INFO,SID);
    }
}
