package com.hello.study.repository;

import com.hello.study.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
  
  //공통으로 사용되는것  > 예제니까 단순하게 map 으로 (실무에서 동시성 문제 생각해야함)
  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;
  
  
  @Override
  public Member save(Member member) {
     member.setId(++sequence);
     store.put(member.getId(), member);
     return member;
  }
  
  @Override
  public Optional<Member> findById(Long id) {
    return Optional.of(store.get(id));
  }
  
  @Override
  public Optional<Member> findByName(String name) {
    return  store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny();
  }
  
  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }
  
  public void clearStore(){
    store.clear();
  }
}
