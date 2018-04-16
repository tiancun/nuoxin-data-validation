package com.nuoxin.data.validation.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * 文件类型检查工具类
 * 根据文件头进行检查
 * @author tiancun
 * @date 2018-04-14
 */
public class FileTypeUtil {

    /**
     * office 文件的文件流
     */
    public static final List<String> OFFICE_FILE_HEADER = new ArrayList<String>(){{
        // 2003版
        add("D0CF11E0");

        // 2007版及以上
        add("504B0304");
    }};


    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    private FileTypeUtil() {
    }
    static {
        //JPEG (jpg)
        FILE_TYPE_MAP.put("jpg", "FFD8FF");

        //PNG (png)
        FILE_TYPE_MAP.put("png", "89504E47");

        //GIF (gif)
        FILE_TYPE_MAP.put("gif", "47494638");

        //TIFF (tif)
        FILE_TYPE_MAP.put("tif", "49492A00");

        //Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("bmp", "424D");

        //CAD (dwg)
        FILE_TYPE_MAP.put("dwg", "41433130");

        //HTML (html)
        FILE_TYPE_MAP.put("html", "68746D6C3E");

        //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("rtf", "7B5C727466");
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");

        //Photoshop (psd)
        FILE_TYPE_MAP.put("psd", "38425053");

        //Email [thorough only] (eml)
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");

        //Outlook Express (dbx)
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");

        //Outlook (pst)
        FILE_TYPE_MAP.put("pst", "2142444E");

        // 2003版word 和 excel和ppt的文件头一样
        FILE_TYPE_MAP.put("xls", "D0CF11E0");
        FILE_TYPE_MAP.put("doc", "D0CF11E0");
        FILE_TYPE_MAP.put("ppt", "D0CF11E0");

        // 2007版以上word 和 excel和ppt的文件头一样
        FILE_TYPE_MAP.put("docx", "504B0304");
        FILE_TYPE_MAP.put("xlsx", "504B0304");
        FILE_TYPE_MAP.put("pptx", "504B0304");

        //MS Access (mdb)
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");

        //WordPerfect (wpd)
        FILE_TYPE_MAP.put("wpd", "FF575043");
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");

        //Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("pdf", "255044462D312E");

        //Quicken (qdf)
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");

        //Windows Password (pwl)
        FILE_TYPE_MAP.put("pwl", "E3828596");

        //Wave (wav)
        FILE_TYPE_MAP.put("wav", "57415645");
        FILE_TYPE_MAP.put("avi", "41564920");

        //Real Audio (ram)
        FILE_TYPE_MAP.put("ram", "2E7261FD");

        //Real Media (rm)
        FILE_TYPE_MAP.put("rm", "2E524D46");
        FILE_TYPE_MAP.put("mpg", "000001BA");

        //Quicktime (mov)
        FILE_TYPE_MAP.put("mov", "6D6F6F76");

        //Windows Media (asf)
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");

        //MIDI (mid)
        FILE_TYPE_MAP.put("mid", "4D546864");
    }


    /**
     * <p>Title:getFileHeader </p>
     * <p>Description: 根据文件路径获取文件头信息</p>
     * @param filePath 文件路径
     * @return 十六进制文件头信息
     * @author 鲁东顺
     * @date 2016-11-23上午10:08:16
     */
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }



    /**
     * <p>Title:getFileHeaderByFileInputStream </p>
     * <p>Description: 根据文件流获取文件头信息,InputStream 自行关闭</p>
     * @param is  文件流
     * @return  十六进制文件头信息
     * @author 鲁东顺
     * @date 2016-11-23上午10:20:27
     */
    public static String getFileHeaderByFileInputStream(InputStream is) {
        if (is == null){
            return null;
        }


        String value = null;
        try {
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);

            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }



    /**
     * <p>Title:bytesToHexString </p>
     * <p>Description: 将要读取文件头信息的文件的byte数组转换成string类型表示 </p>
     * @param src 要读取文件头信息的文件的byte数组
     * @return  文件头信息
     * @author 鲁东顺
     * @date 2016-11-23上午10:09:56
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            /*
            以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            要先和0xFF做一次与运算。这是因为，整个文件流的字节数组中，有很多是负数，
            进行了与运算后，可以将前面的符号位都去掉，这样转换成的16进制字符串最多保留两位，
            如果是正数又小于10，那么转换后只有一位，需要在前面补0，这样做的目的是方便比较，
            取完前四位这个循环就可以终止了。
            （参考自：http://xiaowei-qi-epro-com-cn.iteye.com/blog/1927851）
             */
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream("C:\\Users\\27168\\Desktop\\传智播客C语言入门2.pptx");
        String fileHeaderByFileInputStream = getFileHeaderByFileInputStream(is);
        System.out.println(fileHeaderByFileInputStream);

    }
}
