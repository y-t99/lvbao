package cn.lvbao.util;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author yuanyuan
 * #create 2019-10-24-11:50
 */
public class EncryptUtil {
    private static Cipher decryptcipher;
    private static Cipher encryptcipher;
    static {
        try {
            InputStream keyFileIn1=EncryptUtil.class.getClassLoader().getResourceAsStream("secret.key");
            decryptcipher=getCipher(keyFileIn1,Cipher.DECRYPT_MODE);
            InputStream keyFileIn2=EncryptUtil.class.getClassLoader().getResourceAsStream("secret.key");
            encryptcipher=getCipher(keyFileIn2,Cipher.ENCRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
        获取密钥
        @param keySavedFile 密钥保存文件
     */
    public static void getKey(File keySavedFile) throws IOException, NoSuchAlgorithmException {
        KeyGenerator keygen=KeyGenerator.getInstance("AES");
        SecureRandom random=new SecureRandom();
        keygen.init(random);//初始化密钥发生器
        SecretKey key=keygen.generateKey();//生成密钥
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(keySavedFile));
        out.writeObject(key);//写密钥
    }

    /**
     * 加密
     * @param rawData 原来数据
     * @return 加密的数据
     */
    public static String encrypt(String rawData) throws Exception{
        if (rawData==null){
            return null;
        }
        int mode= Cipher.ENCRYPT_MODE;//模式
        return crypt(rawData,encryptcipher);
    }

    /**
     * 解密
     * @param encryedData
     * @return
     * @throws Exception
     */
    public static  String decrypt(String encryedData) throws Exception {
        if (encryedData==null){
            return null;
        }
        int mode= Cipher.DECRYPT_MODE;//模式
        return decrypt(encryedData,decryptcipher);
    }

    private static Cipher getCipher(InputStream keyFileIn, int mode) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        ObjectInputStream keyIn = new ObjectInputStream(keyFileIn);//密钥获取
        Key key = (Key) keyIn.readObject();
        keyFileIn.close();
        Cipher cipher = Cipher.getInstance("AES");//获取密码器
        cipher.init(mode, key);
        return cipher;
    }

    private static String crypt(String rawData, Cipher cipher) throws Exception {
        byte[] rawDataBytes = rawData.getBytes();
        byte[] encryBytes = cipher.doFinal(rawDataBytes);
        return byteArr2HexStr(encryBytes);
    }

    private static String decrypt(String encryedData, Cipher cipher) throws Exception {
        byte[] encryedBytes=hexStr2ByteArr(encryedData);
        byte[] rawBytes=cipher.doFinal(encryedBytes);
        return new String(rawBytes);
    }
    /**
     * 将字节转为16进制字符串
     * @param encryBytes
     * @return
     * @throws Exception
     */
    private static String byteArr2HexStr(byte[] encryBytes) throws Exception {
        int len = encryBytes.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer encryData = new StringBuffer(len * 2);
        for (int i = 0; i < len; i++) {
            int intTmp = encryBytes[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                encryData.append("0");
            }
            encryData.append(Integer.toString(intTmp, 16));
        }
        return encryData.toString();
    }

    /**
     * 将十六进制字符串转为二进制比特数组
     * @param encryedData
     * @return
     * @throws Exception
     */
    private static byte[] hexStr2ByteArr(String encryedData) throws Exception {
        byte[] encryedBytes = encryedData.getBytes();
        int len = encryedBytes.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[len / 2];
        for (int i = 0; i < len; i = i + 2) {
            String strTmp = new String(encryedBytes, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
}
