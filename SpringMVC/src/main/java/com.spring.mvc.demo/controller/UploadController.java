package com.spring.mvc.demo.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author baiyu
 * <p>
 * 文件上传的控制器
 */

@Controller
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(UploadController.class);
    private static final String BASE_PATH = "/home/baiyu/Desktop/";

    /**
     * 散文集上传
     *
     * @param file 文件
     * @return 结果
     */
    @PostMapping(value = "upload/single_file")
    @ResponseBody
    public String simpleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败请选择文件";
        }
        return saveFile(new File(BASE_PATH), file);
    }


    /**
     * 多文件上传
     *
     * @param files 文件集合
     * @return 结果
     */
    @PostMapping(value = "upload/multi_file")
    @ResponseBody
    public String multiFilesUpload(@RequestParam("file") List<MultipartFile> files) {

        if (files.isEmpty()) {
            return "上传失败请选择文件";
        }


        File baseFileDir = new File(BASE_PATH + "multi");
        if (!baseFileDir.exists()) {
            boolean createDirResult = baseFileDir.mkdir();
            logger.debug(" create dir  /home/baiyu/Desktop/multi " + createDirResult);
        }

        StringBuilder uploadFailFiles = new StringBuilder("Upload file ");
        files.forEach(file -> {
            if (!file.isEmpty()) {
                uploadFailFiles.append(saveFile(baseFileDir, file)).append("\n");
            }
        });

        return uploadFailFiles.toString();
    }

    /**
     * 保存文件
     *
     * @param baseFileDir 目录
     * @param file        文件
     * @return 返回结果
     */
    private String saveFile(File baseFileDir, MultipartFile file) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(new File(baseFileDir, fileName),
                    file.getBytes());

            return fileName + "upload Success ";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName + " upload fail! ";
    }

}
