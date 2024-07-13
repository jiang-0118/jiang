package com.softeem.dao;

import com.softeem.common.ResultInfo;
import com.softeem.entity.Book;
import com.softeem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title: BookDao
 * @Author Jiang
 * @Package com.softeem.dao
 * @Date 2024/7/13 10:30
 * @description:
 */
@Repository
public class BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // BeanPropertyRowMapper: 映射 bean 和 行的关系的对象
    public List<Book> findBooks(){
        return jdbcTemplate.query("select * from book",new BeanPropertyRowMapper<>(Book.class));
    }
    public List<Book> findBooksByCid(Integer cid) {
        return jdbcTemplate.query("select * from book where cid = ?",new BeanPropertyRowMapper<>(Book.class),cid);
    }

    public List<Book> findBooksByKeywords(String key) {
        return jdbcTemplate.query("select * from book where title like ?",new BeanPropertyRowMapper<>(Book.class),("%".concat(key).concat("%")));

    }

    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete from book where id = ?",id);
    }

    public int updateBook(Book book) {
        return jdbcTemplate.update("update book set cover=? ,title=?,author=?,date=?,press=?,abs=?,cid=?,isValid=? where id= ?",
                book.getCover(),book.getTitle(),book.getAuthor(),book.getDate(),book.getPress(),book.getAbs(),book.getCid(),book.getIsValid(),book.getId());
    }

    public int addBook(Book book) {
        return jdbcTemplate.update("insert book values(null,?,?,?,?,?,?,?,?)",
                book.getCover(),book.getTitle(),book.getAuthor(),book.getDate(),book.getPress(),book.getAbs(),book.getCid(),book.getIsValid());
    }
}
