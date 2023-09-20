package com.machao.student.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * author: mc
 * date: 2023-07-26
 */
public abstract class SM4Util {

    /**
     * 加密
     *
     * @param data      待加密字符串
     * @param secretKey 密钥
     * @return
     */
    public static String encrypt(String data, String secretKey) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding", secretKey.getBytes(StandardCharsets.UTF_8));
        return sm4.encryptHex(data);
    }


    /**
     * 解密
     *
     * @param data      待解密字符串
     * @param secretKey 密钥
     * @return
     */
    public static String decrypt(String data, String secretKey) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding", secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            return sm4.decryptStr(data);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        String mobile = "13800001111";
        String secretKey = "ztesoft2021smenc";
        String encrypt = encrypt(mobile, secretKey);
        String decrypt = decrypt(encrypt, secretKey);
        System.out.println(encrypt);
        System.out.println(decrypt);
    }
}
