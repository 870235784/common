import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 测试入库
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(36);
        user.setName("CR7");

        userMapper.insert(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", "CR7");
        userMapper.delete(userQueryWrapper);
    }

    /**
     * 测试乐观锁插件
     */
    @Test
    public void testOptimisticLockerInterceptor() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", "CR7");
        User user = userMapper.selectOne(userQueryWrapper);
        user.setEmail("optimisticLocker@qq.com");
        userMapper.updateById(user);
    }

    /**
     * 测试分页插件
     */
    @Test
    public void testPage() {
        Page<User> page = new Page<>();
        page.setCurrent(0);
        page.setSize(5);
        IPage<User> pageResult = userMapper.selectPage(page, null);
        log.info("查询结果: total = {}, current = {}, size = {}", pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize());
        log.info("查询详情: {}", pageResult.getRecords());
    }
}
