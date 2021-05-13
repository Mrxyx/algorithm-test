package com.mrxyx.algorithm;

public class Main {

    public static void main(String[] args) {
        //斐波那契
        Fib fib = new Fib();
        System.out.println(fib.fibV1(20));
        System.out.println(fib.fibV2(20));
        System.out.println(fib.fibV3(20));
        System.out.println(fib.fibV4(20));

        //零钱兑换
        Coin coin = new Coin();
        Integer[] coins = new Integer[]{1,3,4,9};
        System.out.println(coin.coinChangeV1(coins, 20));
        System.out.println(coin.coinChangeV2(coins, 20));
        System.out.println(coin.coinChangeV3(coins, 20));

        NQueen queen = new NQueen();
        System.out.println(queen.getQueenResult(10));
    }
}
