import com.lagou.edu.AutodeliverApplicaton8098;
import com.lagou.edu.service.ResumeServiceFeignClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = {AutodeliverApplicaton8098.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoDeliverApplicationTest {

    @Autowired
    private ResumeServiceFeignClient resumeFeignClient;

    @Test
    public void testFeignClient(){
        Integer resumeOpenState =
                resumeFeignClient.findResumeOpenState(1545132l);

        System.out.println("=======>>>resumeOpenState：" +
                resumeOpenState);
    }

}
