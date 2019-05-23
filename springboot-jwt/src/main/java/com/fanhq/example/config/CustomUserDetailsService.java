package com.fanhq.example.config;

import com.fanhq.example.entity.User;
import com.fanhq.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author fanhaiqiu
 * @date 2019/2/25
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 自定义实现user创建
         */
        User user = userService.findByUsername(username);
        CustomUserDetail.UserBuilder builder = CustomUserDetail.builder();
        builder.username(user.getUserName());
        builder.password(user.getPassword());
        return builder.build();
    }

}
