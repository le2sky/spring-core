package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 10% 할인 적용")
  public void vip() throws Exception {
    Member member = new Member(1L, "memberA", Grade.VIP);
    int discount = discountPolicy.discount(member, 10000);
    assertThat(discount).isEqualTo(1000);
  }

  @Test
  @DisplayName("VIP가 아니면 할인 적용 X")
  public void test() throws Exception {
    Member member = new Member(1L, "memberA", Grade.BASIC);
    int discount = discountPolicy.discount(member, 10000);
    assertThat(discount).isEqualTo(0);
  }
}