import com.lagou.edu.StreamProducerApplication9090;
import com.lagou.edu.service.IMessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = {StreamProducerApplication9090.class})
@RunWith(value = SpringJUnit4ClassRunner.class)
public class MessageProducerTest {

    @Autowired
    private IMessageProducer messageProducer;

    @Test
    public void testMessage(){
        messageProducer.sendMessage("hello world ");
    }
}
