package com.himly.jpaDynamicSql.service.impl;

import com.himly.jpaDynamicSql.model.Test;
import com.himly.jpaDynamicSql.repository.TestRepository;
import com.himly.jpaDynamicSql.repository.TestRepositorySpec;
import com.himly.jpaDynamicSql.service.TestService;
import com.himly.jpaDynamicSql.validation.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestRepository testRepository;

    @Override
    @NotNull
    public List<Test> getByConditions(@NotBlank @NotNull String name, @NotNull String code, String content) {

        List<Test> tests = testRepository.findAll(TestRepositorySpec.getAllByCondition(name,code));
        System.out.println(tests);

        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        return tests;
    }
}
