package com.stf.web;

import com.stf.domain.Book;
import com.stf.exception.BookNotFoundException;
import com.stf.service.BookService;
import com.stf.service.BookServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;


@Controller
@RequestMapping("/books")
public class BookController {

//    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * 书单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getBookById(@PathVariable long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }


//    /**
//     * 异常处理
//     * @param request
//     * @param e
//     * @return
//     */
//    @ExceptionHandler({Exception.class})
//    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception{
//
//        logger.error("Request URL: {} , Exception: {} ", request.getRequestURL(), e.getMessage());
//
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
//            throw e;
//        }
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url",request.getRequestURL());
//        mav.addObject("exception",e);
//        mav.setViewName("error/error");
//
//        return mav;
//    }
}
