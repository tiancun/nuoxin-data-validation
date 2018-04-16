package com.nuoxin.data.validation.util.sftp;
import java.io.File;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by fenggang on 1/15/18.
 *
 * @author fenggang
 * @date 1/15/18
 */
public class FtpUtil {

    private final static Log logger = LogFactory.getLog(FtpUtil.class);


    /**
     * 从SFTP服务器下载文件
     *
     * @param ftpHost SFTP IP地址
     *
     * @param ftpUserName SFTP 用户名
     *
     * @param ftpPassword SFTP用户名密码
     *
     * @param ftpPort SFTP端口
     *
     * @param ftpPath SFTP服务器中文件所在路径 格式： ftptest/aa
     *
     * @param localPath 下载到本地的位置 格式：H:/download
     *
     * @param fileName 文件名称
     */
    public static void downloadSftpFile(String ftpHost, String ftpUserName,
                                        String ftpPassword, int ftpPort, String ftpPath, String localPath,
                                        String fileName) throws JSchException {
        Session session = null;
        Channel channel = null;

        JSch jsch = new JSch();
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        session.setPassword(ftpPassword);
        session.setTimeout(100000);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp chSftp = (ChannelSftp) channel;

        String ftpFilePath = ftpPath + "/" + fileName;
        String localFilePath = localPath + File.separatorChar + fileName;

        try {
            chSftp.get(ftpFilePath, localPath);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("download error.");
        } finally {
            chSftp.quit();
            channel.disconnect();
            session.disconnect();
        }

    }

    /**
     * 列出目录下的文件
     *
     * @param directory：要列出的目录
     * @return
     * @throws SftpException
     */
    public static Vector listFiles(String ftpHost, String ftpUserName,
                            String ftpPassword, int ftpPort, String directory) throws Exception {
        Session session = null;
        Channel channel = null;

        JSch jsch = new JSch();
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        session.setPassword(ftpPassword);
        session.setTimeout(100000);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp chSftp = (ChannelSftp) channel;


        try {
            return chSftp.ls(directory);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("download error.");
        } finally {
            chSftp.quit();
            channel.disconnect();
            session.disconnect();
        }
        return null;
    }
}
