import com.demo.basic.DemoApplication;
import com.demo.basic.dao.BookInfoMapper;
import com.demo.basic.dao.UserInfoMapper;
import com.demo.basic.vo.domain.UserInfo;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestJave8 {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    BookInfoMapper bookInfoMapper;

    @Test
    public void test() throws Exception {
        Optional<List<UserInfo>> userInfos = getUsers();
        testJava8(userInfos.orElseThrow(() -> new Exception("no data found")));
    }

    private Optional<List<UserInfo>> getUsers() {
        return Optional.ofNullable(userInfoMapper.selectAll());
    }

    private void testJava8(List<UserInfo> userInfos) {
        userInfos.forEach(userInfo -> {
            System.out.println("1 :" + new Gson().toJson(userInfo));
        });
        List<UserInfo> userInfoList2 = userInfos.stream().filter(userInfo -> userInfo.getAge() > 26 && userInfo.getName().equals("Dean wu")).collect(Collectors.toList());

        userInfoList2.forEach(userInfo -> {
            System.out.println("2 :" + new Gson().toJson(userInfo));
        });
    }

    @Test
    public void testFeature() throws Exception {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((result, throwable) -> {
            System.out.println("world");
        });
        System.out.println("start");

        Thread.sleep(5000);
    }

    @Test
    public void testFuture() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            }
        });


    }

}
