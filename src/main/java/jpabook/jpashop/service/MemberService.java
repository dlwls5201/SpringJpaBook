package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 1. class에 @Transactional 경우 public 메소드에는 전부 동작
 * 2. 읽기 전용에는 @Transactional(readOnly = true) 해주는 것이 성능 향상에 도움이 됨
 *
 * @AllArgsConstructor -> 필드를 가지고 생성자를 만들어 줍니다.
 * @RequiredArgsConstructor -> final을 가진 필드를 가지고 생성자를 만들어 줍니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //생성자가 1개인 경우 @Autowired 생략가능
    /*public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicationMember(member); //중복 호원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단일 회워 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
