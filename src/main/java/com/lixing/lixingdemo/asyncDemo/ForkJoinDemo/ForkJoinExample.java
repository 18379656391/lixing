package com.lixing.lixingdemo.asyncDemo.ForkJoinDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-06-21 15:28:40
 */
public class ForkJoinExample {
    public static final Integer MAX = 200;

    // RecursiveAction 无结果返回的任务
    // RecursiveTask 有返回结果的任务
    // CountedComplete 无返回值任务，完成任务后可以触发回调
    static class CalcForJoinTask extends RecursiveTask<Integer> {
        // 子任务的开始计算的值
        private Integer startValue;
        // 子任务结束计算的值
        private Integer endValue;

        public CalcForJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        protected Integer compute() {
            // 未超过最大值，不做任务拆分
            if (endValue - startValue < MAX) {
                System.out.println(Thread.currentThread().getName()+"开始计算：startValue:" + startValue + ";endValue:" + endValue);
                Integer total = 0;
                for (int i = this.startValue; i <= this.endValue; i++) {
                    total += i;
                }
                return total;
            }
            // 任务拆分
            CalcForJoinTask subTask = new CalcForJoinTask(startValue, (startValue + endValue) / 2);
            // fork：让task异步执行
            subTask.fork();

            CalcForJoinTask subTask2 = new CalcForJoinTask((startValue + endValue) / 2 + 1, endValue);
            subTask2.fork();

            // join:让task同步执行，可以获取返回值
            return subTask.join() + subTask2.join();
        }

        public static void main(String[] args) {
            CalcForJoinTask task = new CalcForJoinTask(1, 1000);
            // 专门用来运行ForkJoinTask的线程池，并不会为每个子任务都创建一个单独的线程，相反，池中的每个线程都有自己的双端队列用于存储任务（这种架构使用了=工作窃取=算法来平衡线程的工作负载）
            // 当一个线程自己的双端队列取任务为空时，就从另一个线程的双端队列尾部或者全局入口队列中获取任务
            ForkJoinPool pool = new ForkJoinPool();
            // pool.invoke();提交任务并一直阻塞直到任务执行完成返回合并结果
            // pool.execute();异步执行任务，无返回值
            // pool.submit();异步执行任务，返回task本身，可以通过task.get()方法获取合并之后的结果
            ForkJoinTask<Integer> submit = pool.submit(task);

            try {
                Integer result = submit.get();
                System.out.println(result);
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }
    }

}
