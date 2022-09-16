package com.zerock;

import com.zerock.domain.Member;
import com.zerock.domain.Profile;
import com.zerock.persistence.MemberRepository;
import com.zerock.persistence.ProfileRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log // Lombok의 로그를 사용할 때 이용
@Commit // 테스트 결과를 데이터베이스에 commit
public class ProfileTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Test // 테스트 회원 데이터 삽입.
    public void testInsertMembers() {

        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);

            memberRepository.save(member);
        });
    } // end method

    @Test
    public void testInsertProfile() {

        Member member = new Member();
        member.setUid("user1");

        for (int i = 1; i < 5; i++) {
            Profile profile1 = new Profile();
            profile1.setFname("face" + i + ".jpg");

            if (i == 1) {
                profile1.setCurrent(true);
            }

            profile1.setMember(member);
            profileRepository.save(profile1);
        }
    } // end method
}
