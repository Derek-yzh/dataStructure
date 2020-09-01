package org.example.dataInterface;

/**
 * 2020-07-02 10:49:59
 *关于稀疏数组的操作
 * 稀疏化 原始化
 * 将数组稀疏化存入文件保存 将文件中的数组原始化读取
 */
public interface SparseArray {

    /**
     * 将二维数组转为稀疏数组
     * @param array
     * @return
     */
    public int[][] toSparse(int[][] array);

    /**
     * 将稀疏数组转为二维数组
     * @param sparseArray
     * @return
     */
    public int[][] backSparse(int[][] sparseArray);

    /**
     * 格式打印数组
     * @param array
     */
    public void printArray(int[][] array);

    /**
     * 将数组稀疏化并保存到文件中
     * @param array
     * @param file
     */
    public void saveSparseArray(int[][] array, String file);

    /**
     * 从文件中取出稀疏数组并原始化
     * @param file
     * @return
     */
    public int[][] getSparseArray(String file);

}
