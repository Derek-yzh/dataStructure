package org.example.dataStructure;

import org.example.algorithm.SparseArrayImpl;
import org.junit.jupiter.api.Test;

/**
 * 2020-07-02 09:40:48
 * 测试SparseArray
 */
public class TestSparse {

    @Test
    public void testSparse(){
        int[][] chessArr = new int[8][8];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        org.example.dataInterface.SparseArray sa = new SparseArrayImpl();

        //稀疏化
        int[][] sparseArray = sa.toSparse(chessArr);
        System.out.println("稀疏化后的数组：");
        sa.printArray(sparseArray);
        //原始化
        int[][] array = sa.backSparse(sparseArray);
        System.out.println("原始化的数组：");
        sa.printArray(array);

        //将数组稀疏化并存到文件中
        sa.saveSparseArray(chessArr,"sparse.txt");
        //从文件中读取稀疏化的数组并原始化
        int[][] array2 = sa.getSparseArray("sparse.txt");
        System.out.println("从文件读取的数组：");
        sa.printArray(array2);
    }

}
