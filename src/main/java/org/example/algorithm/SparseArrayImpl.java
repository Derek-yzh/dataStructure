package org.example.algorithm;

import java.io.*;

public class SparseArrayImpl implements org.example.dataInterface.SparseArray {

    @Override
    public int[][] toSparse(int[][] array) {
        int sum = 0;
        int r = 0;
        int c =0;

        //获取行、列、数据总数
        for (int[] row : array) {
            r++;
            for (int data : row) {
                if (r == 1){
                    c++;
                }
                if (data != 0){
                    sum++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparse = new int[sum + 1][3];
        sparse[0][0] = r;
        sparse[0][1] = c;
        sparse[0][2] = sum;

        //向稀疏数组存放数据
        int row = 1;//稀疏数组存放数据的当前行
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (array[i][j] != 0){
                    int data = array[i][j];
                    sparse[row][0] = i;
                    sparse[row][1] = j;
                    sparse[row][2] = data;
                    row++;
                }
            }
        }

        return sparse;
    }

    @Override
    public int[][] backSparse(int[][] sparseArray) {
        //获取原数组行、列、数据总数
        int r = sparseArray[0][0];
        int c = sparseArray[0][1];
        int sum = sparseArray[0][2];

        //创建原数组以及赋值
        int[][] array = new int[r][c];
        for (int i = 1; i <= sum; i++) {
            int row = sparseArray[i][0];
            int column = sparseArray[i][1];
            int data = sparseArray[i][2];
            array[row][column] = data;
        }

        return array;
    }

    @Override
    public void printArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }

    @Override
    public void saveSparseArray(int[][] array, String file) {
        int[][] sparseArray = this.toSparse(array);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (int[] row : sparseArray) {
                for (int data : row) {
                    bw.write(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int[][] getSparseArray(String file) {
        BufferedReader br = null;
        int[][] array = null;
        try {
            br = new BufferedReader(new FileReader(file));
            int row = br.read();
            int column = br.read();
            int sum = br.read();
            array = new int[row][column];
            for (int i = 1; i <= sum; i++) {
                array[br.read()][br.read()] = br.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}
