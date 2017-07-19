package com.turing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.turing.manage.entity.Student;
import com.turing.manage.mapper.StudentMapper;
import com.turing.manage.util.BeanUtils;
import com.turing.redisConfig.InitRedis;
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
@EnableTransactionManagement	//启用事务注解@Transactional
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
