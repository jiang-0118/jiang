package com.softeem;

import com.softeem.common.ResultInfo;
import com.softeem.entity.Book;
import com.softeem.service.IBookService;
import com.softeem.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BookManagerTests {

    @Autowired
    private IUserService userService;


    @Test
    public void testLogin(){
        ResultInfo result = userService.login("zhangsan123", "123");
        System.out.println(result);
    }
    @Test
    public void testRegister(){
        ResultInfo result = userService.register("aaa666", "123");
        System.out.println(result);
    }

    @Autowired
    private IBookService bookService;

    @Test
    public void testFindBooks(){
        ResultInfo books = bookService.findBooks();
        System.out.println(books);
    }
    @Test
    public void testFindBooksByCid(){
        ResultInfo books = bookService.findBooksByCid(3);
        System.out.println(books);
    }
    @Test
    public void testFindBooksByKeywords(){
        ResultInfo books = bookService.findBooksByKeywords("森");
        System.out.println(books);
    }
    @Test
    public void testDeleteById(){
        ResultInfo resultInfo = bookService.deleteById(1);
        System.out.println(resultInfo);
    }
    @Test
    public void testAddBook(){
        ResultInfo resultInfo = bookService.addBook(new Book("www.baidu.com","赌棍","cs","04-01","软帝出品","软帝",1,1));
        System.out.println(resultInfo);
    }
    @Test
    public void testUpdateBook(){
        ResultInfo resultInfo = bookService.updateBook(new Book(92,"www.baidu.com","赌棍","陈森","04-01","软帝出品","软帝",1,1));
        System.out.println(resultInfo);
    }

}
