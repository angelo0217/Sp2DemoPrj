package com.demo.basic.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class UserBookTestVo {
    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("book_price")
    private Integer bookPrice;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("user_age")
    private Integer age;
    @JsonProperty("throw_exception")
    private boolean throwEx;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isThrowEx() {
        return throwEx;
    }

    public void setThrowEx(boolean throwEx) {
        this.throwEx = throwEx;
    }
}
