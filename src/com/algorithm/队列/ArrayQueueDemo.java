package com.algorithm.队列;

import java.util.Scanner;

//数组模拟队列
//队列先入先出
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：取出数据");
            System.out.println("h(head)：显示头部数据");
            key = scanner.next().charAt(0);//初始化key为0
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                case 'a':
                    if(queue.isFull()){
                        throw new RuntimeException("队列已满，无法添加数据");
                    }
                    int i = scanner.nextInt();
                    try {
                        queue.addQueue(i);
                    } catch (Exception e) {
                        System.out.println("e.getMessage() = " + e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println("queue.getQueue() = " + queue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("queue.haedQueue() = " + queue.haedQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("退出！");
    }
}


class ArrayQueue {
    private int maxSize;//表示队列的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr; //该数组用于存放数据，模拟队列


    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部的前一个位置
        rear = -1;//指向队列尾的位置
    }


    public void addQueue(int n) {
        //1.判断队列是否已满
        if (isFull()) {
            throw new RuntimeException("队列已满了");
        }
        //rear原本指向-1，添值时从0开始
        rear++;//从指向尾部位置添加数
        arr[rear] = n;
//        arr[++rear]=n;//等同上式
    }

    //获取一个队列数据（先进先出），这个是取出不是查询，会导致头索引增加，则前面的数据取不到(arr中数据并没有丢失)
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，取出失败");
        }
        front++;//从指向头部位置取数,并且++之后取不到之前的数了
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，取数失败");
        }
        /*增强for循环与普通fori的区别：
            1.遍历的i是值不是索引！arr[i]很可能索引越界
            2.增强for循环遍历出值的时候再给其赋值不影响原来数据，fori如果给arr[i]赋值则会影响
        */
//        for (int i : arr) {
//            System.out.println(i);
//            System.out.println(arr[i]);
//            System.out.printf("arr[%d]=%d\n", i, arr[i]);
//        }

        for (int i = 0; i <arr.length ; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列头部数据
    public int haedQueue() {
        if (isEmpty()) {
            throw new RuntimeException("数组为空");
        }
        return arr[front + 1];
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
//       return rear==-1;
        return rear == front;
    }

    //todo cy 由于取数据导致前面的数据取不到，浪费队列容量，需要改善（环形队列）

}

