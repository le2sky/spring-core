package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  @Test
  public void statefulServiceSingleton() throws Exception {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        TestConfig.class);

    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    statefulService1.order("userA", 10000);
    statefulService2.order("userB", 20000);

    int price = statefulService1.getPrice();
    System.out.println("price = " + price);

    assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}