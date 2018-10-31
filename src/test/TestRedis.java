import com.andy.vs.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set(){
        redisTemplate.opsForValue().set("測試:test12345","testValue趙");
    }
}
