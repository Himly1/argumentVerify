package com.himly.jpaDynamicSql.service;

import com.himly.jpaDynamicSql.model.Test;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {

    public List<Test> getByConditions(String name, String code, String content);
}
