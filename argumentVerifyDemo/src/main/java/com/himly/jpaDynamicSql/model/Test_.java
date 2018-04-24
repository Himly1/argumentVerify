package com.himly.jpaDynamicSql.model;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Test.class)
public class Test_ {

    public static volatile SingularAttribute<Test,Long> id;

    public static volatile SingularAttribute<Test,String> name;

    public static volatile SingularAttribute<Test,String> code;

    public static volatile SingularAttribute<Test,String> content;
}
