package com.anish.calabashbros;

/*
我们发现它要的是一维数组，但是我们的矩阵是二维数组
最简单的解决办法：传入二维数组，但是改为一维数组，处理时按一维数组处理，
然后在将结果解释成二维数组
但是这样搞就不大有面向对象的感觉了 那我们就在扩展BubbleSorter的功能，让他可以处理二维数组
*/
public class BubbleSorter<T extends Comparable<T>> implements Sorter<T>
{

    private T[] a;
    private T[][] matrixArr;

    @Override
    public void load(T[] a)
    {
        this.a = a;
    }
    public void load(T[][] a)
    {
        this.matrixArr = a;
    }

    private void swap(int i, int j)
    {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort()
    {
        if(matrixArr == null)
        {
            boolean sorted = false;
            while (!sorted)
            {
                sorted = true;
                for (int i = 0; i < a.length - 1; i++)
                {
                    if (a[i].compareTo(a[i + 1]) > 0)
                    {
                        swap(i, i + 1);
                        sorted = false;
                    }
                }
            }
        }
        else
        {
            boolean sorted = false;
            while (!sorted)
            {
                sorted = true;
                for (int i = 0; i < matrixArr.length; i++)
                {
                    for (int j = 0; j < matrixArr[0].length; j++)
                    {
                        for (int k = i; k < matrixArr.length; k++)
                        {
                            for (int l = j; l < matrixArr[0].length; l++)
                            {
                                if((l == matrixArr.length - 1) && k != matrixArr.length - 1)
                                {
                                    if(matrixArr[k][l].compareTo(matrixArr[k + 1][0]) > 0)
                                    {
                                        swap(k, l, k +1, 0);
                                    }
                                }
                                else
                                {
                                    if(l != matrixArr[k].length - 1)
                                    {
                                        if((matrixArr[k][l].compareTo(matrixArr[k][l + 1]) > 0))
                                        {
                                            swap(k, l, k, l + 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void swap(int firstI, int firstJ, int secondI, int secondJ)
    {
        T temp;
        temp = matrixArr[firstI][firstJ];
        matrixArr[firstI][firstJ] = matrixArr[secondI][secondJ];
        matrixArr[secondI][secondJ] = temp;
        plan += "" + matrixArr[firstI][firstJ] + "<->" + matrixArr[secondI][secondJ] + "\n";
    }
    @Override
    public String getPlan()
    {
        return this.plan;
    }

}