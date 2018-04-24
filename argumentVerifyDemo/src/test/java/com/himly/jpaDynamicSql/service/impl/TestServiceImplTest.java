package com.himly.jpaDynamicSql.service.impl;

import com.himly.jpaDynamicSql.service.TestService;
import com.himly.jpaDynamicSql.validation.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * create_at:MaZheng
 * create_by:${date} ${time}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceImplTest {

    @Autowired
    TestService testService;

    @Test
    public void getByConditions() throws Exception {
        testService.getByConditions("name",null,"content");
    }

    public void  test1(@NotNull String name) {


    }
}