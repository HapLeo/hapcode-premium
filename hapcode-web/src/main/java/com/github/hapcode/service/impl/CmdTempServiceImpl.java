package com.github.hapcode.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.github.hapcode.cmd.CodeGenCmd;
import com.github.hapcode.service.CmdTempService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

@Service
public class CmdTempServiceImpl implements CmdTempService {


    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    @Override
    public void storeCmdTemp(CodeGenCmd codeGenCmd) {

//        CodeGenCmd codeGenCmd1 = readCmdTemp(codeGenCmd.getRootPath());
//        if (codeGenCmd1 != null) {
//            return;
//        }

        Properties properties = new Properties();
        //File file = File.createTempFile("hapcode-cmd-temp", ".properties");
        File file = new File(TEMP_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        System.out.println(file.getAbsolutePath());
        File tempFile = new File(file.getAbsolutePath() + "/hapcode-cmd-temp.properties");
        try (FileWriter fileWriter = new FileWriter(tempFile); FileReader fileReader = new FileReader(tempFile);) {

            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            properties.load(fileReader);
            properties.put(codeGenCmd.getRootPath() + "&" + codeGenCmd.getModuleName(), JSON.toJSONString(codeGenCmd));
            properties.store(fileWriter, "");
            fileWriter.flush();
        } catch (Throwable throwable) {
            throw new RuntimeException("创建临时文件异常：", throwable.getCause());
        }

    }

    @Override
    public CodeGenCmd readCmdTemp(String rootPath, String moduleName) {
        Properties properties = new Properties();
        //String tempdir = System.getProperty("java.io.tmpdir");
        File file = new File(TEMP_DIR);
        if (!file.exists()) {
            return null;
        }
        String fileName = file.getAbsolutePath() + "/hapcode-cmd-temp.properties";
        File propertyFile = new File(fileName);
        if (!propertyFile.exists()) {
            return null;
        }
        try (FileReader fileReader = new FileReader(propertyFile)) {

            properties.load(fileReader);
            String key = rootPath + "&" + moduleName;
            String str = String.valueOf(properties.get(key));
            if (ObjectUtil.isNotEmpty(str)) {
                return JSON.parseObject(str, CodeGenCmd.class);
            }
            return null;
        } catch (Throwable throwable) {
            throw new RuntimeException("创建临时文件异常：", throwable);
        }

    }
}
