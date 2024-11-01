package org.sunmoon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.sunmoon.backend.entity.Member;
import org.sunmoon.backend.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public Map<String, Object> login(
            @RequestBody Map<String, String> params
    ) {
        // findByEmailAndPassword 메소드에서 이름 인자를 제거
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if (member != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", member.getId());
            response.put("name", member.getName()); // 이름 추가

            return response; // JSON 형식으로 응답
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "로그인 정보가 존재하지 않습니다.");
    }
}


