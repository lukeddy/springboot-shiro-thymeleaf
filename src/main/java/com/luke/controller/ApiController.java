package com.luke.controller;

import com.luke.exception.BookNotFoundException;
import com.luke.exception.UserNotFoundException;
import com.luke.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;


@RestController
@RequestMapping("/api")
public class ApiController {


    private HashMap<Integer,String> map;

    @PostConstruct
    public void initDate(){
        map=new HashMap<>(3);
        map.put(111,"book1");
        map.put(222,"book3");
        map.put(333,"book3");
    }

    @GetMapping("/books")
    public Result listBooks(){
        return Result.ok("成功",map);
    }

    @GetMapping("/book/{id}")
    public Result getBook(@PathVariable("id")Integer id){
        if(!map.containsKey(id)){
            throw new BookNotFoundException("书不存在");
        }
        return Result.ok("成功",map.get(id));
    }

    @GetMapping("/user/{id}")
    public String notExist(@PathVariable("id")Integer id){
        throw new UserNotFoundException("用户找不到异常");
    }
}
