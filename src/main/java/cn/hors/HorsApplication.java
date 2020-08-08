package cn.hors;

import cn.jasonone.ueditor.EnableUeditor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.hors.mapper")
@SpringBootApplication
@EnableUeditor
public class HorsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HorsApplication.class, args);
    }
}
