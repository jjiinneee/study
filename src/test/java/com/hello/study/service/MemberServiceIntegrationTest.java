package com.hello.study.service;

import com.hello.study.domain.Member;
import com.hello.study.repository.MemberRepository;
import com.hello.study.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
  @Autowired
  MemberService memberService;
  
  @Autowired
  MemberRepository memberRepository;
  
  
  @Test
  void join() {
    //given(상황주어졌을때)
    Member member = new Member();
    member.setName("spring");
    
    //when(실행했을때)
    Long saveId = memberService.join(member);
    
    //then(결과가 나와야함)
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
    
  }
  
  @Test
  public void duplicateMemberException(){
    //given
    Member member1 = new Member();
    member1.setName("spring");
    
    Member member2 = new Member();
    member2.setName("spring");
    
    //when
    memberService.join(member1);
    // 이방법도 있고
    //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    
    
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    
    //then
    
  }
  
}
