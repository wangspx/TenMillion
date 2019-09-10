package com.spwang.luck.tenmillion.service;

import com.spwang.luck.tenmillion.repository.entity.History;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author spwang Created on 2019/9/10 at 8:48
 * @version 1.0.0
 */
@Service
public class _500SpiderService extends DoubleColorSpiderService<History> {

    private static final String URL = "http://sports.sina.com.cn/l/kaijiang/index.shtml";

    private static final String ISSUE_PATTERN = "<font class=\"cfont2\"><strong>.*?</strong></font>";

    private static final Pattern ISSUE_COMPILE = Pattern.compile(ISSUE_PATTERN);

    //    @Resource
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getSource() {
        return restTemplate.getForObject(URL, String.class);
    }

    @Override
    public History handleResult(String content) {
        System.out.println(content);
        Matcher matcher = ISSUE_COMPILE.matcher(content);

        if (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        return null;
    }

    public static void main(String[] args) {
        SpiderService service = new _500SpiderService();
        service.execute();
    }
}
