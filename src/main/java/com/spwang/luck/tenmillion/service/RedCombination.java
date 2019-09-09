package com.spwang.luck.tenmillion.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;

/**
 * @author spwang Created on 2019/8/26 at 15:32
 * @version 1.0.0
 */
@Slf4j
public abstract class RedCombination {

    private int number;

    /**
     * 生成红球所有组合
     */
    public void generate() {
        generateRedCombination();
    }

    /**
     * 红球组合处理
     *
     * @param index 组合序号
     * @param Combination 红球组合
     */
    abstract void resultHandler(Integer index, List<String> Combination);

    private void generateRedCombination() {
        List<String> tmp = new ArrayList<>();

        String[] redBox = new String[33];
        for (int i = 0; i < 33; i++) {
            redBox[i] = String.format("%02d", i + 1);
        }

        combine(0, 6, redBox, tmp);

        log.debug("generate red combination done...");
    }

    private void combine(int index, int k, String[] arr, List<String> tmp) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmp.add(arr[i]);

                resultHandler(number++, tmp);

                tmp.remove(arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmp.add(arr[i]);
                combine(i + 1, k - 1, arr, tmp);
                tmp.remove(arr[i]);
            }
        }
    }
}
