package com.demo.basic.controller;

import com.demo.basic.config.DynamicRoutingDataSource;
import com.demo.basic.config.MysqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019/4/14
 *
 * @author dean
 * @since 1.0
 */
@RestController
public class DbController {
    @Autowired
    ConfigurableBeanFactory beanFactory;

    @Autowired
    ConfigurableApplicationContext ctx;
    @GetMapping("/closedb")
    public String close() throws Exception{
//        //mysqlDataSource
//        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, null);
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) ctx.getAutowireCapableBeanFactory();

//        for(String beanName : ctx.getBeanDefinitionNames()){
//            System.out.println(beanName);
////            registry.removeBeanDefinition(beanName);
//        }
//        tmp = registry.getBeanDefinition("mysqlDataSource")
//        registry.removeBeanDefinition("mysqlDataSource");

        ctx.stop();
//        beanFactory.
        return "success";
    }
//    public static BeanDefinition tmp  = null;
    @GetMapping("/addDb")
    public String addDb() throws Exception{
//        //mysqlDataSource
//        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, null);
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) ctx.getAutowireCapableBeanFactory();
//        for(String beanName : ctx.getBeanDefinitionNames()){
//            System.out.println(beanName);
////            registry.removeBeanDefinition(beanName);
//        }

//        GenericBeanDefinition gbd = new GenericBeanDefinition();
//        gbd.setBeanClass(DynamicRoutingDataSource.class);
//        gbd.setSynthetic(true);
//        gbd.setPrimary(true);
//        registry.registerBeanDefinition("mysqlDataSource",gbd);
        ctx.start();
//        beanFactory.
        return "success";
    }
}
