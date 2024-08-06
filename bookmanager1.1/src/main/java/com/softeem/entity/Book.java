package com.softeem.entity;

import lombok.*;

/**
 * @Title: Book
 * @Author Jiang
 * @Package com.softeem.entity
 * @Date 2024/7/13 10:27
 * @description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String cover;
    private String title;
    private String author;
    private String date;
    private String press;
    private String abs;
    private int cid;
    private int isValid;


    public Book(String cover, String title, String author, String date, String press, String abs, int cid, int isValid) {
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.date = date;
        this.press = press;
        this.abs = abs;
        this.cid = cid;
        this.isValid = isValid;
    }
}
