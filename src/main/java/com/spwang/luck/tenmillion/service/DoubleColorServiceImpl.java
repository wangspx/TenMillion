package com.spwang.luck.tenmillion.service;

import com.spwang.luck.tenmillion.Repository.RedCombinationRepository;
import com.spwang.luck.tenmillion.entity.AllCombination;
import com.spwang.luck.tenmillion.entity.RedCombination;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author spwang 2019/7/20 12:56 PM
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class DoubleColorServiceImpl {

    private List<String> tmp = new ArrayList<>();

    private List<String> result = new ArrayList<>(1200000);

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

//    @Resource
//    private AllCombinationRepository allCombinationRepository;
//
//    @Resource
//    private RedCombinationRepository redCombinationRepository;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public void start() {
        System.out.println("start...");
        String[] com = new String[33];
        for (int i = 0; i < 33; i++) {
            com[i] = String.format("%02d", i + 1);
        }
        int k = 6;
        combine(0, k, com);

        System.out.println(result.size());


        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        RedCombinationRepository redCombinationRepository = sqlSession.getMapper(RedCombinationRepository.class);

        long start = System.currentTimeMillis();
        List<RedCombination> list = new ArrayList<>(100032);
        result.forEach(s -> {
            RedCombination redCombination = new RedCombination();
            redCombination.setOne(s.substring(0,1));
            redCombination.setTwo(s.substring(2,3));
            redCombination.setThree(s.substring(4,5));
            redCombination.setFour(s.substring(6,7));
            redCombination.setFive(s.substring(8,9));
            redCombination.setSix(s.substring(10,11));
            redCombination.setUpdateTime(new Date());
            list.add(redCombination);
            if (list.size() >= 100000) {
                long start1 = System.currentTimeMillis();
                redCombinationRepository.insertList(list);
                sqlSession.commit();
                System.out.println(System.currentTimeMillis() - start1);
                list.clear();
            }
        });
        redCombinationRepository.insertList(list);
        sqlSession.commit();
        System.out.println(System.currentTimeMillis() - start);




//        List<AllCombination> list = new ArrayList<>(100032);
//        for (String s : result) {
//            addBlue(list, s);
//            if (list.size() >= 100000) {
//                allCombinationRepository.saveAll(list);
//                System.out.println("提交一次事务，数据总条数：" + allCombinationRepository.count());
//                list.clear();
//            }
//        }
    }

    /**
     * 组合
     * 按一定的顺序取出元素，就是组合,元素个数[C arr.len 3]
     *
     * @param index 元素位置
     * @param k     选取的元素个数
     * @param arr   数组
     */
    private void combine(int index, int k, String... arr) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmp.add(arr[i]);
                result.add(StringUtils.join(tmp, ""));
                tmp.remove(arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmp.add(arr[i]);
                combine(i + 1, k - 1, arr);
                tmp.remove(arr[i]);
            }
        }
    }

    private void addBlue(List<AllCombination> list, String s) {
        for (int i = 1; i <= 16; i++) {
            AllCombination entity = new AllCombination();
            entity.setCombination(s + String.format("%02d", i));
            list.add(entity);
        }
    }

    private void addBlueAndSave(String s) {
        fixedThreadPool.execute(() -> {
            List<AllCombination> list = new ArrayList<>(16);
            for (int i = 1; i <= 16; i++) {
                AllCombination entity = new AllCombination();
                entity.setCombination(s + String.format("%02d", i));
                list.add(entity);
            }
//            allCombinationRepository.saveAll(list);
        });
    }

    private void addBlueAndSave1(String s) {
        for (int i = 1; i <= 16; i++) {
            int finalI = i;
            fixedThreadPool.execute(() -> {
                AllCombination entity = new AllCombination();
                entity.setCombination(s + String.format("%02d", finalI));
//                allCombinationRepository.save(entity);
                System.out.println(entity.toString());
            });
        }
    }
}
