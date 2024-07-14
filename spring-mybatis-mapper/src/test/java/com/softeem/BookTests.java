package com.softeem;

import com.softeem.entity.Book;
import com.softeem.mapper.BookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BookTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void test1(){
        System.out.println(bookMapper.findBooksByParameters(
                null,
                "大",
                "大"
        ));
    }


    @Test
    public void test2(){
        Book book = new Book();
        book.setTitle("三体");
        book.setId(77);
        int row = bookMapper.updateBook(book);
        System.out.println(row);
    }


    @Test
    public void test3(){
        System.out.println(bookMapper.findBooksByIds(List.of(85,86,92,4,5)));
    }

}
