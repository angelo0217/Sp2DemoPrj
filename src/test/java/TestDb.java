import com.demo.basic.DemoApplication;
import com.demo.basic.dao.SqlMapper;
import com.demo.basic.dao.UserInfoMapper;
import com.demo.basic.service.repository.MemberRepository;
import com.demo.basic.service.repository.impl.UserInfoRepositoryImpl;
import com.demo.basic.util.EncryptUtil;
import com.demo.basic.vo.domain.UserInfo;
import com.demo.basic.vo.domain.UserInfoOps;
import com.demo.basic.vo.security.UserDetailVo;
import com.google.gson.Gson;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestDb {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserInfoRepositoryImpl userInfoRepositoryImpl;

    @Test
    @Async
    public void testInsert() throws Exception{
//        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
//        System.out.println(new Gson().toJson(userInfo));
//
        Optional<UserInfo> userInfoOps = userInfoMapper.getOptionalUser(1);
        System.out.println(new Gson().toJson(userInfoOps.orElseThrow(() -> new Exception("not found"))));

//        List<Optional<UserInfo>> userList = userInfoMapper.getOptionalUserAll();
//        System.out.println(new Gson().toJson(userList));
//        userList.forEach(userInfo1 -> {
//            try {
//                System.out.println(new Gson().toJson(userInfo1.orElseThrow(() -> new Exception("not found"))));
//            }catch (Exception e){
//
//            }
//        });
    }

    @Autowired
    BeanFactory beanFactory;
    @Test
    public void testEn(){

        StringEncryptor stringEncryptor = (StringEncryptor) beanFactory.getBean("jasyptStringEncryptor");
        System.out.println(stringEncryptor.encrypt("123456"));
        System.out.println(stringEncryptor.encrypt("123456"));
    }


    @Autowired
    SqlMapper sqlMapper;

    @Test
    public void testSqlMapper(){
//        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) beanFactory.getBean("mySqlSessionFactory");
////        SqlMapper sqlMapper = new SqlMapper(sqlSessionFactory.openSession());
        System.out.println(new Gson().toJson(sqlMapper.selectOne("select * from user_info where seq = 1")));
    }

    @Test
    public void contextLoads() {
        System.out.println(EncryptUtil.getSecurityPwd("12345"));
    }

    @Autowired
    MemberRepository memberRepository;
    @Test
    public void printUser() throws Exception{
        System.out.println(new Gson().toJson(new UserDetailVo(memberRepository.selectByNameOps("admin").orElseThrow( ()-> new UsernameNotFoundException("not found user")))));
    }
}
