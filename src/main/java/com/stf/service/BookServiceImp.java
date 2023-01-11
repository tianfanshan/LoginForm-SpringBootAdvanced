package com.stf.service;

import com.stf.domain.Book;
import com.stf.domain.BookRepository;
import com.stf.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 获取一个书单信息
     * @param id
     * @return
     */
    @Override
    public Book getBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()){
            throw new BookNotFoundException("书单id: " + id + "不存在");
        }
        return book.get();
    }
}
