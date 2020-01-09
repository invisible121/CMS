package luoma.test_cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("luoma.test_cms.Dao")
public class TestCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCmsApplication.class, args);
    }

}
