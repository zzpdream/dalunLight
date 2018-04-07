package com.zz.led.cmd.client2server.user;


import com.zz.led.protocol.C2SProtocol;
import com.zz.led.protocol.client2server.C2SCIDCode;
import com.zz.led.protocol.client2server.C2SSIDCode;

/**
 * Created by munin on 2017/12/14.
 */

public class UserCmd {
    //    登陆指令
    public static final byte[] login(String user, String pwd) {
        byte[] CID = C2SCIDCode.M5039.getBytes();
        if(user.contains("@")){
            byte[] USERINFO = ("|"+user+"||"+pwd).getBytes();
            return C2SProtocol.getByteWithText(CID,USERINFO, C2SSIDCode.SID);
        }else{
            byte[] USERINFO = (user+"|||"+pwd).getBytes();
            return C2SProtocol.getByteWithText(CID,USERINFO,C2SSIDCode.SID);
        }
    }

    //    第三方登陆指令
    public static final byte[] loginThird(String userId) {
        byte[] CID = C2SCIDCode.M5039.getBytes();
        byte[] USERINFO = ("||"+userId+"|").getBytes();
        return C2SProtocol.getByteWithText(CID,USERINFO, C2SSIDCode.SID);
    }

    //     登出指令
    public static final byte[] loginOut() {
        byte[] CID = C2SCIDCode.M5043.getBytes();
        return C2SProtocol.getByteNoText(CID, C2SSIDCode.SID);
    }

    //    注册指令
    public static final byte[] register(String userInfo) {
        byte[] CID = C2SCIDCode.M5037.getBytes();
        byte[] TEXTINFO = userInfo.getBytes();
        return  C2SProtocol.getByteWithText(CID,TEXTINFO, C2SSIDCode.SID);
    }

    //     获取验证码
    public static final byte[] code(String mode, String address) {
        byte[] CID = C2SCIDCode.M5035.getBytes();
        byte[] INFO = (mode+address).getBytes();
        return C2SProtocol.getByteWithText(CID,INFO,C2SSIDCode.SID);
    }

    //    忘记密码
    public static final byte[] forget(String user, String pwd, String code) {
        byte[] CID = C2SCIDCode.M5049.getBytes();
        byte[] INFO = null;
        if(user.contains("@"))
            INFO = ("|"+user+"||"+pwd+"|"+code).getBytes();
        else
            INFO = (user+"|||"+pwd+"|"+code).getBytes();
        return C2SProtocol.getByteWithText(CID,INFO, C2SSIDCode.SID);
    }
// 更新个人信息
    public static final byte[] update(String info){
        byte[] CID = C2SCIDCode.M5047.getBytes();
        byte[] PERSONINFO = info.getBytes();
        return C2SProtocol.getByteWithText(CID,PERSONINFO, C2SSIDCode.SID);
    }

}
