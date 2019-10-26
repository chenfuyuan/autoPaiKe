import com.ass.manager.service.autoPaike.GAAlgorithm;
import com.ass.manager.service.autoPaike.Individual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext*.xml")
public class Ta {
    @Autowired
    private GAAlgorithm gaAlgorithm;

    @Autowired
    private Individual individual;
    @Test
    public void demo01() {
//        individual.init("大四上");
        gaAlgorithm.caculte("大一下");
    }
}
