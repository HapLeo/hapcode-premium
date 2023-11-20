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


    @Override
    public void storeCmdTemp(CodeGenCmd codeGenCmd) {


        try {
            Properties properties = new Properties();
            //File file = File.createTempFile("hapcode-cmd-temp", ".properties");
            String tempdir = System.getProperty("java.io.tmpdir");
            File file = new File(tempdir);
            if (!file.exists()) {
                file.mkdirs();
            }
            System.out.println(file.getAbsolutePath());
            File tempFile = new File(file.getAbsolutePath() + "hapcode-cmd-temp.properties");
            if (!tempFile.exists()){
                tempFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(tempFile);
            FileReader fileReader = new FileReader(tempFile);
            properties.load(fileReader);
            properties.put(codeGenCmd.getRootPath(), JSON.toJSONString(codeGenCmd));
            properties.store(fileWriter, "");
            fileWriter.flush();
        } catch (Throwable throwable) {
            throw new RuntimeException("创建临时文件异常：", throwable.getCause());
        }

    }

    @Override
    public CodeGenCmd readCmdTemp(String rootPath) {

        try {
            Properties properties = new Properties();
            String tempdir = System.getProperty("java.io.tmpdir");
            File file = new File(tempdir);
            if (!file.exists()) {
                return null;
            }
            FileReader fileReader = new FileReader(file.getAbsolutePath() + "hapcode-cmd-temp.properties");
            properties.load(fileReader);
            String str = String.valueOf(properties.get(rootPath));
            if (ObjectUtil.isNotEmpty(str)) {
                return JSON.parseObject(str, CodeGenCmd.class);
            }
            return null;
        } catch (Throwable throwable) {
            throw new RuntimeException("创建临时文件异常：", throwable.getCause());
        }

    }
}
