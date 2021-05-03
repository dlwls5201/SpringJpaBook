package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //테스트 에서는 기본적으로 @Transactional 사용시 테스트 완료 후 Rollback을 실행합니다.
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    //@Rollback(value = false)
    public void 회원가입() {
        //Given
        Member member = new Member();
        member.setName("kim");

        //When
        Long savedId = memberService.join(member);

        //Then
        Assert.assertEquals(member, memberService.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //When
        memberService.join(member1);
        memberService.join(member2); //에외가 발생해야 한다.

        //Then
        Assert.fail("에외가 발생해야 한다.");
    }
}