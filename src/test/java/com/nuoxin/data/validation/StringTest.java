package com.nuoxin.data.validation;

import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.nuoxin.data.validation.util.RemoteExecuteCommand;
import com.nuoxin.data.validation.util.sftp.FtpUtil;
import com.nuoxin.data.validation.util.sftp.SFTPChannel;
import com.nuoxin.data.validation.util.sftp.SFTPConstants;
import org.junit.Test;
import springfox.documentation.spring.web.json.Json;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fenggang on 1/9/18.
 *
 * @author fenggang
 * @date 1/9/18
 */
public class StringTest {

    @Test
    public void test(){
//        String hostpitalName = "北京医院";
//        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(hostpitalName);
//        System.out.println(m.find());

        BigDecimal bd = new BigDecimal("1.3077650996E10");
        System.out.println(bd.toPlainString());
    }

    @Test
    public void remote(){
        RemoteExecuteCommand rec=new RemoteExecuteCommand("47.95.22.53", "root","rec2017Nx!");
        //执行命令
        System.out.println(rec.execute("ifconfig"));
        //执行脚本
        rec.execute("/root/anaconda3/bin/python /usr/local/tfidf/temp/tf_idf.py /usr/local/tfidf/temp/input/doc1.txt > /usr/local/tfidf/temp/input/doc1.log.txt");
        //这个方法与上面最大的区别就是，上面的方法，不管执行成功与否都返回，
        //这个方法呢，如果命令或者脚本执行错误将返回空字符串
        rec.executeSuccess("ifconfig");
    }


    @Test
    public void sftp() throws Exception {

        Map<String, String> sftpDetails = new HashMap<>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "47.95.22.53");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "root");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "rec2017Nx!");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");

        String src = "/Users/fenggang/doc1.log"; // 本地文件名
        String dst = "/usr/local/tfidf/temp/input/doc1.log"; // 目标文件名

        SFTPChannel channel = new SFTPChannel();
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);

        /**
         * 代码段1
         OutputStream out = chSftp.put(dst, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
         byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
         int read;
         if (out != null) {
         System.out.println("Start to read input stream");
         InputStream is = new FileInputStream(src);
         do {
         read = is.read(buff, 0, buff.length);
         if (read > 0) {
         out.write(buff, 0, read);
         }
         out.flush();
         } while (read >= 0);
         System.out.println("input stream read done.");
         }
         **/

        chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2

        // chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // 代码段3

        chSftp.quit();
        channel.closeChannel();
    }


    @Test
    public void run() throws Exception {

        String ftpHost = "47.95.22.53";
        String ftpUserName = "root";
        String ftpPassword = "rec2017Nx!";
        int ftpPort = 22;
        String ftpPath = "/root/code/doctorIDF/result/keywords/";
        String localPath = "/Users/fenggang";
        String file = "2017-12-27";
//        FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, fileName);
        Vector<ChannelSftp.LsEntry> vector = FtpUtil.listFiles(ftpHost, ftpUserName, ftpPassword, ftpPort,"/root/code/doctorIDF/result/keywords/");
        for (ChannelSftp.LsEntry map:vector) {
            if(map.getFilename().indexOf(file)>=0){
                FtpUtil.downloadSftpFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, localPath, map.getFilename().replaceAll("\"",""));
            }
        }
    }




}
