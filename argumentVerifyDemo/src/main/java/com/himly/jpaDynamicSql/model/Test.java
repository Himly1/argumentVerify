package com.himly.jpaDynamicSql.model;


import javax.persistence.*;

@Entity
@Table(name = "test_dynamic_sql")
public class Test {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String content;


    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
