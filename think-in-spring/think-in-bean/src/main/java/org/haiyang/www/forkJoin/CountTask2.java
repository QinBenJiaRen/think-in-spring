package org.haiyang.www.forkJoin;

public class CountTask2 {

    public static void main(String[] args) {

        Long startTimeMills = System.currentTimeMillis();
        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        Long endTimeMills = System.currentTimeMillis();
        System.out.println("总共耗时:" + (endTimeMills - startTimeMills) / 1000 + "s");
    }
}
