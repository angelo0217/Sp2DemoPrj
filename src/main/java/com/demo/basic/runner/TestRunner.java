package com.demo.basic.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-12-21
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Component
@Order(1)
public class TestRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("***runner*** 啟動伺服器後的第一個工作點");
    }
}
