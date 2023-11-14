package com.seed.jdk;

import com.seed.user.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://www.xjx100.cn/news/50835.html?action=onClick
 */
public class SteamApi {
    public static void main(String[] args) {
        List<User>userList = new ArrayList<User>(){{
            add(User.builder().name("zhangsan1").id(1L).email("z@123.com").build());
            add(User.builder().name("zhangsan2").id(1L).email("z@123.com").build());
            add(User.builder().name("lisi1").id(2L).email("l@123.com").build());
            add(User.builder().name("lisi1").id(2L).email("l@123.com").build());
        }};

        Map<Long,List<User>> map = userList.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println(map.toString());

    }
}
