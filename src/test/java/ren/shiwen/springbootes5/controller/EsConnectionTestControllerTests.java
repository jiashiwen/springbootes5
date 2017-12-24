package ren.shiwen.springbootes5.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用于测试Controller
 * @author Jiashiwen
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EsConnectionTestControllerTests {
//    @Autowired
//    private EsConnectionTestController controller;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("/testescon",
                String.class)).contains("5");
    }
}
