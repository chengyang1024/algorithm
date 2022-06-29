package com.algorithm.队列;

import java.util.Scanner;

//环形队列测试1111111
public class AnnularArrayQueueDemo {

    public static void main(String[] args) {
        AnnularArrayQueue queue = new AnnularArrayQueue(4);
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
                    if (queue.isFull()) {
                        System.out.println("数组已满，无法添加！");
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
                default:
                    System.out.println("输入有误重新输入");
                    break;
            }
        }
        System.out.println("退出！");
    }

}

//环形队列
class AnnularArrayQueue extends ArrayQueue {
    private int maxSize;//表示队列的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr; //该数组用于存放数据，模拟队列

    public AnnularArrayQueue(int arrMaxSize) {
        super(arrMaxSize);
        front = 0;//指向队列的第一个元素，初始值0
        rear = 0;//指向队列最后一个元素的后一个位置
        maxSize=arrMaxSize;//表示队列的最大容量
        arr = new int[maxSize];
    }

    @Override
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满，不能添加数据");
        }
        arr[rear] = n;
//       rear=rear+1;//由于是环形队列，+1会导致索引越界
        rear = (rear + 1 + maxSize) % maxSize;
    }

    @Override
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取数");
        }
//        return arr[front];//如果直接返回，则front无法变化
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 遍历所有元素
     */
    @Override
    public void showQueue() {
        //从front开始遍历，遍历*个（需求出元素个数）
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法展示");
        }
        //队列中有效数据个数
        int size = (rear + maxSize - front) % maxSize;
        /*首先由于环形队列，rear可能在front前面，
        取两者之间差值不能代表数据个数，
        需要将rear+maxSize确保一定大于front，再相减
        （确保增加元素的方向和计算个数的方向是相同的）*/
        for (int i = front; i < front+size; i++) {
            System.out.println("----展示数据开始----");
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    @Override
    public int haedQueue() {
        return arr[front];
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return rear==front;
    }
}



