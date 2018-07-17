package com.qmm.multi.service.impl;

import com.qmm.multi.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by qinmengmei on 2018/7/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestServiceImplTest {

    @Autowired
    private TestService testService;

    @Test
    public void testList() throws Exception {
        List<String> list = testService.list();
        log.info(list.toString());
    }
}