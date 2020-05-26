package com.zt.gp.controller;


import com.zt.gp.bean.BaseResult;
import com.zt.gp.entity.Picture;
import com.zt.gp.entity.User;
import com.zt.gp.service.IPictureService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zt
 * @since 2020-05-26
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    IPictureService pictureService;

    @PostMapping("add")
    public BaseResult add(MultipartFile file, Integer id) throws IOException {
        String filename = file.getOriginalFilename();
        String suffix = StringUtils.substringAfterLast(filename, ".");
        Picture picture = new Picture();
        picture.setTaskId(id);
        picture.setSuffix(suffix);
        pictureService.save(picture);
        String name = picture.getId() + "." + suffix;
        String absolutePath = new ClassPathResource("/").getFile().getAbsolutePath();
        String targetPath = StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(absolutePath, File.separator), File.separator);
        String dir = targetPath + File.separator + "upload" + File.separator
                + id + File.separator;
        File temp = new File(dir + "temp");
        if (!temp.exists())
            temp.mkdirs();
        File target = new File(dir + "target");
        if (!target.exists())
            target.mkdirs();

        String fileName = dir + "source" + File.separator + name;
        FileUtils.writeByteArrayToFile(new File(fileName), file.getBytes());
        return BaseResult.ok();
    }
}

