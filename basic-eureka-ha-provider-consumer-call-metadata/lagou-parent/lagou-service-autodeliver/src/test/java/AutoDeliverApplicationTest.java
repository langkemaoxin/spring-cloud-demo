import com.lagou.edu.AutodeliverApplicaton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//设置SpringBoot启动程序入口
@SpringBootTest(classes = {AutodeliverApplicaton.class})

//替换Junit默认运行器
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoDeliverApplicationTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testInstanceMetaData(){

        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");

        for (int i = 0; i < instances.size(); i++) {
            ServiceInstance serviceInstance = instances.get(i);
            System.out.println(serviceInstance);
        }
    }
}
