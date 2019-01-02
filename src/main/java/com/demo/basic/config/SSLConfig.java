//package com.demo.basic.config;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 參考網址 https://blog.csdn.net/u011857433/article/details/80159943
// * Created on 2018/12/27
// *
// * @author dean
// * @email loveangelo0217@gmail.com
// * @since 1.0
// */
//@Configuration
//public class SSLConfig {
//    @Value("${server.port}")
//    protected int serverPort;
//
//    @Bean
//    public TomcatServletWebServerFactory servletContainer() { //springboot2 新变化
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//
//            @Override
//            protected void postProcessContext(Context context) {
//
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    @Bean
//    public Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8880);
//        connector.setSecure(false);
//        connector.setRedirectPort(serverPort);
//        return connector;
//    }
//    // 第一組給 key-store
//    // keytool -genkey -v -alias qq -keyalg RSA -storetype PKCS12 -keystore keystore.p12
//    // keytool -keystore D:\keystore.p12 -export -alias qq -file D:\keystore.cer
//    // 第二組給 trust-store
//    // keytool -import -v -file D:\keystore.cer  -keystore D:\keystoreF.p12
//    //https://localhost:8888/demo/oauth/token?username=user&password=12345&grant_type=password&scope=read&client_id=admin&client_secret=abc123
//}
