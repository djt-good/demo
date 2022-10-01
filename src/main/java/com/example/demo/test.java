package com.example.demo;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Set;

public class test {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.83.131",6379);
//        jedis.set("k1","v1");
//        jedis.set("k2","v2");
//        jedis.set("k3","v3");
//        Set<String> keys = jedis.keys("*");
//        for(String key:keys){
//            System.out.println(key);
//        }
        veryCode("15527991393");
//    getRegistCode("15527991393","942402");

    }

    public static void getRegistCode(String phone,String code){
        Jedis jedis = new Jedis("192.168.83.131",6379);
        String Count= "code"+phone+":code";
        String s = jedis.get(Count);
        if(s.equals(code)){
            System.out.println("成功!");
        }else {
            System.out.println("失败!");
            jedis.close();
            return;
        }



    }

    public static  void veryCode(String phone){
        Jedis jedis = new Jedis("192.168.83.131",6379);
        String Count= "code"+phone+":count";
        String Code= "code"+phone+":code";
        String count =jedis.get(Count);
        if(count ==null){
            jedis.setex(Count,24*24*24,"1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(Count);
        }else if (Integer.parseInt(count) >2){
            System.out.println("次数上限，明天再来！！");
            jedis.close();
        }

        String code = getCode();
        String set = jedis.set(Code, code);
        jedis.close();
    }
    public static String  getCode(){

        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            code +=i1 ;

        }
        return code;
    }
}
