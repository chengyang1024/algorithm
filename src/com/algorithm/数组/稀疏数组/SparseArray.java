package com.algorithm.数组.稀疏数组;


public class SparseArray {
    /**
     * 定义二维数组-转换成稀疏数组-存入本地-读取成稀疏数组-转换成二维数组
     * 0：表示没有子 1：表示黑子 2：表示篮子
     *
     * @param args
     */
    public static void main(String[] args) {
        //一，定义二维数组
        System.out.println("------定义二维数组----");
        int chessArr1[][] = new int[11][11];
        //赋值
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 2;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                //不换行打印，"%d\t"表示每次间隔
                System.out.printf("%d\t", anInt);
            }
            //另起一行
            System.out.println();
        }
        //二，转换成稀疏数组(行数等于普通数组的非0个数，列是3)
        //1.先遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.定义稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //3.定义数组第一组数据（表头）
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //4.将二维数组值赋值给稀疏数组(需要获取二维数组非0数据)
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    //给稀疏数组赋值
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }
        //5.遍历稀疏数组
        System.out.println("----稀疏数组打印----");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }

        //三，todo  存入本地
        //四，todo 读取成稀疏数组
        //五，转换成二维数组

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];


        /*
        1。索引越界异常！chessArr2数组的长度是11，索引最大是10，
        sparseArr第一行中记录二维数组长度的11（sparseArr[0][0]=11），而chessArr2[11][11]导致索引越界
        2。sparseArr的[0][*]定义的是二维数组的长度和值的total，作用在定义的时候，恢复数组则从[1][*]开始
        */
       /* for (int i = 0; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]= sparseArr[i][2];
        }*/

        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]= sparseArr[i][2];
        }

        System.out.println("-----转换后的二维数组-----");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
    }
}
