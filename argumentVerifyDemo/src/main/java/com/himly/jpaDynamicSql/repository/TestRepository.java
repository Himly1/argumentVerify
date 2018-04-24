package com.himly.jpaDynamicSql.repository;

import com.himly.jpaDynamicSql.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TestRepository extends JpaRepository<Test,Long>,JpaSpecificationExecutor<Test> {

}
