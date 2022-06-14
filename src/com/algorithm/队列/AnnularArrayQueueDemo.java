package com.algorithm.队列;

import java.util.Scanner;

//环形队列
public class AnnularArrayQueueDemo {

    public static void main(String[] args) {
        AnnularArrayQueue queue = new AnnularArrayQueue(3);
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

//环形队列
class AnnularArrayQueue  extends ArrayQueue{


    public AnnularArrayQueue(int arrMaxSize) {
        super(arrMaxSize);
    }

    @Override
    public void addQueue(int n) {
        super.addQueue(n);
    }

    @Override
    public int getQueue() {
        return super.getQueue();
    }

    @Override
    public void showQueue() {
        super.showQueue();
    }

    @Override
    public int haedQueue() {
        return super.haedQueue();
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
}



