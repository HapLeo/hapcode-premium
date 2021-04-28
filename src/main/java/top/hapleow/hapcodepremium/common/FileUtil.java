package top.hapleow.hapcodepremium.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wuyulin
 * @date 2020/9/16
 */
public class FileUtil {


    /**
     * 将内容输出到文件
     *
     * @param content  内容
     * @param fileName 文件名
     * @param filePath 文件路径
     */
    public static void createFile(String content, String fileName, String filePath) {

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(filePath + fileName);
        if (!file.exists()) {

            try {

                file.createNewFile();

            } catch (IOException e) {
                System.out.println("创建文件失败！" + e.getMessage());
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
