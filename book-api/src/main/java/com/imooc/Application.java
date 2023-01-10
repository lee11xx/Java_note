package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Lee
 * @description:
 * @package: com.imooc
 * @date: Created in 2022/12/26 19:21
 */

@SpringBootApplication
@MapperScan(basePackages = "com.imooc.mapper")
@ComponentScan(basePackages = {"com.imooc", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
