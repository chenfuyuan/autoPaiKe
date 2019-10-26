package com.ass.manager.service.autoPaike;

import java.util.Arrays;
import java.util.Comparator;

public class GAUtils {
    public static int random(double i) {
        return (int) (i * Math.random());
    }

    /**
     * 对基因进行排序
     */
    public static void genesSort(int[][]genes) {
        /**
         * 定义一个比较器，比较2维数组的每一行第一列，降序排序
         */
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return  o1[0] < o2[0] ? -1 : 1;
            }
        };
        /*对genes进行排序，使其通过time排序*/
        Arrays.sort(genes, comparator);
    }
}
