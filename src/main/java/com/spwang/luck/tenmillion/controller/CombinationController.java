package com.spwang.luck.tenmillion.controller;

import com.spwang.luck.tenmillion.service.DoubleColorService;
import com.spwang.luck.tenmillion.service.DoubleColorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author spwang Created on 2019/8/26 at 15:10
 * @version 1.0.0
 */
@RestController
@RequestMapping("combination")
public class CombinationController implements VersionController {

    @Resource
    @Qualifier("doubleColorServiceImpl2")
    private DoubleColorService service;

    @GetMapping("/generate/red")
    public void redCombination() {
        service.generateRedCombination();
    }

    @GetMapping("/generate/all")
    public void allCombination() {
        service.generateAllCombination();
    }
}
