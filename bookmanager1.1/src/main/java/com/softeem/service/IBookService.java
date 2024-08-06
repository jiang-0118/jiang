package com.softeem.service;

import com.softeem.common.ResultInfo;

import com.softeem.entity.Book;

public interface IBookService {

    /**
     * 查询所有图书
     * @return
     */
    ResultInfo findBooks();

    /**
     * 根据类型编号查询图书
     * @param cid
     * @return
     */
    ResultInfo findBooksByCid(Integer cid);

    /**
     * 根据关键字 匹配 标题 作者 出版社，模糊查询
     * @param key
     * @return
     */
    ResultInfo findBooksByKeywords(String key);


    /**
     * 根据ID 删除图书
     * @param id
     * @return
     */
    ResultInfo deleteById(Integer id);


    /**
     * 更新图书
     * @param book
     * @return
     */
    ResultInfo updateBook(Book book);

    /**
     * 添加图书
     * @param book
     * @return
     */
    ResultInfo addBook(Book book);
}
