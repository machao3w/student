package com.machao.student.utils;

import org.hashids.Hashids;

/**
 * author: mc
 * data: 2019/12/2 13:37
 */

public class Hashidse {

    private static final String KEY = "SDFASGFDFG1123";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz1234567890";
    private  static final int MIN_HASH_LENGTH = 8;


    public static String encodeStr(String str){
        Hashids hashids = new Hashids(KEY,MIN_HASH_LENGTH,ALPHABET);
        return hashids.encodeHex(str);
    }

    public static String encodeStr(String str,String key){
        Hashids hashids = new Hashids(key,MIN_HASH_LENGTH,ALPHABET);
        return hashids.encodeHex(str);
    }

    public static String encodeInt(int i){
        Hashids hashids = new Hashids(KEY,MIN_HASH_LENGTH,ALPHABET);
        return hashids.encode(i);
    }

    public static String decodeStr(String str){
        Hashids hashids = new Hashids(KEY,MIN_HASH_LENGTH,ALPHABET);
        return hashids.decodeHex(str);
    }

    public static String decodeStr(String str,String key){
        Hashids hashids = new Hashids(key,MIN_HASH_LENGTH,ALPHABET);
        return hashids.decodeHex(str);
    }

    public static  long decodeInt(String str){
        Hashids hashids = new Hashids(KEY,MIN_HASH_LENGTH,ALPHABET);
        long[] longs = hashids.decode(str);
        return longs[0];
    }
}
