package org.haiyang.www.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

    private static final Integer THRESHOLD = 100000;

    private Integer start;

    private Integer end;

    public CountTask(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = 0; i <= end; i++) {
                sum+= i;
            }
        } else {
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等到子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(1, Integer.MAX_VALUE);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            Long startTimeMills = System.currentTimeMillis();
            System.out.println(result.get());
            Long endTimeMills = System.currentTimeMillis();
            System.out.println("总共耗时:" + (endTimeMills - startTimeMills) / 1000 + "s");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
