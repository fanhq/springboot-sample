package com.fanhq.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fanhq.example.dto.BaseResponse;
import com.fanhq.example.repository.TokenRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fanhaiqiu
 * @date 2019/6/25
 */

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    private static final String TOKEN_KEY = "token";
    private static final String CHARSET_UTF_8 = "UTF-8";
    private static final String ACCESS_TOKEN = "access-token";

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = request.getHeader(ACCESS_TOKEN);
        if (antMatcher(request) || checkToken(tokenValue)) {
            filterChain.doFilter(request, response);
            return;
        }
        response.setCharacterEncoding(CHARSET_UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = response.getWriter();
        JSONObject token = new JSONObject();
        token.put(TOKEN_KEY, tokenValue);
        writer.write(JSON.toJSONString(BaseResponse.invalid(token)));
        writer.flush();
        writer.close();
    }

    /**
     * token校验
     *
     * @param tokenValue
     * @return
     */
    private boolean checkToken(String tokenValue) {
        LOGGER.info("【begin check token】:{}", tokenValue);
        if (StringUtils.isEmpty(tokenValue)) {
            return false;
        }
        return tokenRepository.existsByTokenAndExpireTimeIsGreaterThan(tokenValue, System.currentTimeMillis());
    }

    /**
     * 开放请求
     *
     * @return
     */
    private boolean antMatcher(HttpServletRequest request) {
        AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/open/**");
        return requestMatcher.matches(request);
    }
}
