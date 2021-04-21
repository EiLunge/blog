package com.sx.blog.sys.mapper;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.common.pojo.Follow;

/**
 * @author 邓宇阳
 * @date 2019年12月30日
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserPowerMaapperTest {

    @Autowired
    private UserPowerMapper userPowerMapper;

    @Test
    void testIsFristLike() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdateLikeStatus() {
        fail("Not yet implemented");
    }

    // @Test
    // void testAddLike() {
    // userPowerMapper.addLike(42, 1);
    // }

    @Test
    void testAddFollow() {
        fail("Not yet implemented");
    }

    @Test
    void testAddFans() {
        fail("Not yet implemented");
    }

    @Test
    void testDelFollow() {
        fail("Not yet implemented");
    }

    @Test
    void testDelFans() {
        fail("Not yet implemented");
    }

    @Test
    void testSelect() {
        Follow f = userPowerMapper.selectFollow(2, 1);
        System.out.println(f.getFollowId());
    }

    @Test
    void testAddCollectArticle() {
        fail("Not yet implemented");
    }

    @Test
    void testDelCollectArticle() {
        fail("Not yet implemented");
    }

    @Test
    void testAddReportArticle() {
        fail("Not yet implemented");
    }
}
