import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tca.common.data.mybatis.test.MybatisTestApplication;
import com.tca.common.data.mybatis.test.entity.User;
import com.tca.common.data.mybatis.test.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoua
 * @Date 2020/12/22
 */
@SpringBootTest(classes = MybatisTestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(36);
        user.setName("CR7");

        userMapper.insert(user);
    }

    @Test
    public void testDelete() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", "CR7");
        userMapper.delete(userQueryWrapper);
    }

}
