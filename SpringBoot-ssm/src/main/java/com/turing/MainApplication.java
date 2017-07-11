package com.turing;

import java.util.Date;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Description：springboot主程序，项目在此运行
 * Company：图灵云教育
 * @author zhaogang
 * @date 2017年7月8日 22:24:00
 */
@SpringBootApplication
//如果没有设置数据库连接，则用下面的@SpringBootApplication注解
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})

//@ComponentScan(basePackages="com.ltzc.ccoc.utils")
//@ComponentScan(basePackages="com.ltzc.ccoc.servers.properties")
//@ComponentScan(basePackages="com.ltzc.ccoc.user.service")
@Controller
@MapperScan("com.turing.manage.mapper")	//对mapper包的扫描
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
	@RequestMapping("/")
    //@ResponseBody
    String home() {
        //return "Hello World!";
        return "redirect:/student/query.action";
    }
	
	@RequestMapping("/now")
	@ResponseBody
    String hehe() {
        return "现在时间：" + (new Date()).toLocaleString();
    }

}
