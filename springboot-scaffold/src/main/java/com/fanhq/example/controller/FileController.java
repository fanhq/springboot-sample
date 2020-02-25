package com.fanhq.example.controller;


import com.fanhq.example.dto.BaseResponse;
import com.fanhq.example.exception.CustomBizException;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * @author fanhaiqiu
 * @date 2019/6/27
 */
@RestController
@RequestMapping(value = "file")
public class FileController {

    /**
     * 常用图片格式
     */
    private static final List<String> IMAGE_LIST = Lists.newArrayList(".JPG", ".JPEG", ".PNG", ".GIF");

    private static final String POINT = ".";

    /**
     * 头像上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "img/upload")
    public BaseResponse upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new CustomBizException("文件不能为空");
        }
        // 获取文件的后缀名
        String originalName = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalName) || originalName.lastIndexOf(POINT) == -1) {
            throw new CustomBizException("图片格式不正确");
        }
        String suffixName = originalName.substring(originalName.lastIndexOf(POINT)).toUpperCase();
        if (this.checkImg(suffixName)) {
            throw new CustomBizException("图片格式不正确");
        }
        return BaseResponse.success(null);
    }

    /**
     * 校验图片格式
     *
     * @param suffixName
     * @return
     */
    private boolean checkImg(String suffixName) {
        if (IMAGE_LIST.contains(suffixName)) {
            return false;
        }
        return true;
    }
}
