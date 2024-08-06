package com.softeem.mapper;

import com.softeem.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-14
 */
public interface BookMapper{
    // 根据图书名称 作者 出版社查询
    List<Book> findBooksByParameters(
            @Param("title") String title,
            @Param("author") String author,
            @Param("press") String press
    );


    int updateBook(Book book);


    // 根据多个ID查询图书
    List<Book> findBooksByIds(List<Integer> ids);

}
