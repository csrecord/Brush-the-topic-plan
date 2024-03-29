package com.huaweioj;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                int key = sc.nextInt();
                int val = sc.nextInt();
                arr[i][0] = key;
                arr[i][1] = val;
            }
            System.out.println(solution(arr));
        }
    }

    private static int solution(int[][] arr) {

        int minSLA = 0;
        int time = 0;
        int score = 0;
        int maxSLA = findMaxSLA(arr);
        while (time < maxSLA) {
            //先找到最小的SLA所在的索引
            List<Integer> minList = findMinKey(arr, minSLA);
            //判断最小索引是否存在
            if (minList.size() <= 0) {
                break;
            }
            //从最小索引列表中取得最小索引
            minSLA = arr[minList.get(0)][0];
            //判断最小的SLA是否超过了已用时间
            if (minSLA < time) {
                //已超过，重新寻找最小的SLA索引列表
                minList = findMinKey(arr, minSLA);
            } else {
                //未超过，寻找积分最多的工单执行
                int[] maxScore = findMaxScore(minList, arr);
                time++;
                score += maxScore[0];
                //执行过的值需要去掉
                arr[maxScore[1]][0] = 0;
                arr[maxScore[1]][1] = 0;
            }
        }

        return score;
    }

    //寻找积分最大工单
    private static int[] findMaxScore(List<Integer> minList, int[][] arrList) {
        int maxScore = 0;
        int maxIndex = 0;
        for (int i = 0; i < minList.size(); i++) {

            if (arrList[minList.get(i)][1] > maxScore) {
                maxIndex = i;
                maxScore = arrList[minList.get(i)][1];
            }
        }
        return new int[]{maxScore, maxIndex};
    }

    //寻找最大的SLA
    private static int findMaxSLA(int[][] arr) {
        int maxSLA = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] > maxSLA) {
                maxSLA = arr[i][0];
            }
        }
        return maxSLA;
    }

    //寻找最接近已用时间的最小SLA
    private static List<Integer> findMinKey(int[][] arr, int low) {
        List<Integer> list = new LinkedList<>();
        int min = 7 * 105 + 1;
        for (int[] ints : arr) {
            if (ints[0] < min && ints[0] > low) {
                min = ints[0];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == min) {
                list.add(i);
            }
        }
        return list;
    }
}

