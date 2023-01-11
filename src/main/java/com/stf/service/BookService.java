package com.stf.service;

import com.stf.domain.Book;

import java.util.Optional;

public interface BookService {

    Book getBookById(long id);
}
