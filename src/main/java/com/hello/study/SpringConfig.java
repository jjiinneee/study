package com.hello.study;

import com.hello.study.repository.MemberRepository;
import com.hello.study.repository.MemoryMemberRepository;
import com.hello.study.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
  
  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }
  
  @Bean
  public MemberRepository memberRepository(){
    return new MemoryMemberRepository();
  }
}
