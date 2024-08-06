package com.softeem.service.impl;

import com.softeem.common.ResultInfo;
import com.softeem.common.ResultInfoUtil;
import com.softeem.dao.BookDao;
import com.softeem.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softeem.entity.Book;


/**
 * @Title: BookServiceImpl
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/13 10:15
 * @description:
 */
@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public ResultInfo findBooks() {

        return ResultInfoUtil.buildSuccess("查询成功",bookDao.findBooks());
    }

    @Override
    public ResultInfo findBooksByCid(Integer cid) {
        return ResultInfoUtil.buildSuccess("查询成功",bookDao.findBooksByCid(cid));
    }

    @Override
    public ResultInfo findBooksByKeywords(String key) {
        return ResultInfoUtil.buildSuccess("查询成功",bookDao.findBooksByKeywords(key));
    }

    @Override
    public ResultInfo deleteById(Integer id) {
        int i = bookDao.deleteById(id);
        if (i==0){
            return ResultInfoUtil.buildError("删除失败");
        }
        return ResultInfoUtil.buildSuccess("删除成功","id:"+id);
    }

    @Override
    public ResultInfo updateBook(Book book) {
        int i = bookDao.updateBook(book);
        if (i==0){
            return ResultInfoUtil.buildError("更新失败");
        }
        return ResultInfoUtil.buildSuccess("更新成功",book);
    }

    @Override
    public ResultInfo addBook(Book book) {
        int i= bookDao.addBook(book);
        if (i==0){
            return ResultInfoUtil.buildError("添加失败");
        }
        return ResultInfoUtil.buildSuccess("添加成功",book);
    }


}
