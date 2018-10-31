import com.andy.vs.App;
import com.andy.vs.service.impl.IndicatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class TestKafka {

    @Autowired
    private IndicatorService indicatorService;

    @Test
    public void sendKafkaMsg() {
        indicatorService.sendMessage("Topic1", "This is test msg");
    }
}
