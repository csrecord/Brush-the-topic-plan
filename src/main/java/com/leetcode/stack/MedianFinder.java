package com.leetcode.stack;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {
    Queue<Integer> A, B;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        //小顶堆
        A = new PriorityQueue<>();
        //大顶堆,接收两个参数并返回他们的值
        B = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}
