package cn.hors;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.hors.mapper")
@SpringBootApplication
public class HorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorsApplication.class, args);
    }

}
