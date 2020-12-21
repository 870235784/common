import com.tca.common.cache.redis.bean.CacheHelper;
import com.tca.common.cache.test.CacheTestApplication;
import com.tca.common.cache.test.req.CacheRedisReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoua
 * @Date 2020/12/22
 */
@SpringBootTest(classes = CacheTestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTemplateTest {

    @Autowired
    private CacheHelper cacheHelper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {

        RedisSerializer<?> keySerializer = stringRedisTemplate.getKeySerializer();
        RedisSerializer keySerializer1 = redisTemplate.getKeySerializer();

        log.info("stringRedisTemplate: {}", keySerializer);
        log.info("redisTemplate: {}", keySerializer1);

        redisTemplate.opsForValue().set("test", new CacheRedisReq("hello", "world"));

        CacheRedisReq req = (CacheRedisReq)redisTemplate.opsForValue().get("test");
        log.info("req = {}", req);


        String req1 = stringRedisTemplate.opsForValue().get("test");
        log.info("req1 = {}", req1);

        stringRedisTemplate.setValueSerializer(redisTemplate.getValueSerializer());
        String req2 = stringRedisTemplate.opsForValue().get("test");
        log.info("req2 = {}", req2);
    }

}
