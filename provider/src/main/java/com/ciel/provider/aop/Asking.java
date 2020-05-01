package com.ciel.provider.aop;

public class Asking {

    public static void main(String[] args) {

        zmc(String::trim);
    }

    public static void  zmc(Ack ack){
        Class<? extends Ack> aClass = ack.getClass();
        System.out.println(ack);
    }
}
