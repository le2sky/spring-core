package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() throws Exception {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);

    MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean(MemberRepository.class);
    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();

    System.out.println("Member#memberRepository1 = " + memberRepository1);
    System.out.println("Order#memberRepository2 = " + memberRepository2);
    System.out.println("memberRepository = " + memberRepository);

    assertThat(memberRepository1).isSameAs(memberRepository);
    assertThat(memberRepository2).isSameAs(memberRepository);
  }

  @Test
  void configurationDeep() throws Exception {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean = " + bean.getClass());
  }
}
