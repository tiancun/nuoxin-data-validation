package com.nuoxin.data.validation.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;  
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
  
public class CheckUtil{  
	

     /** 
      * 判断数据是否为空 
      * @param str 字符串 
      * @return 
      */  
     public static boolean isEmpty(String str){  
         if(null == str || str.trim().equals("")){  
             return false;  
         }  
         return true;  
     }  
     
     /** 
      * 格式化日期为：2017-12-16
      * @param date 
      * @return 
      */  
     public static String getNumberDate(String date){  
         String year = date.substring(0, 4);  
         String month = leaveZero(date.substring(4, 6));  
         String day = leaveZero(date.substring(6,10));  
         return year +  month +  day;  
     }  
     
     
     
     
     /**
      * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
      * 
      * @param strDate
      * @return
      */
   public static Date strToDateLong(String strDate) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      ParsePosition pos = new ParsePosition(0);
      Date strtodate = formatter.parse(strDate, pos);
      return strtodate;
   }
   
   
   /**
      * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
      * 
      * @param dateDate
      * @return
      */
   public static String dateToStrLong(Date dateDate) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateString = formatter.format(dateDate);
      return dateString;
   }
   
     /**
      * 获取现在时间
      * 
      * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
      */
   public static String getNowDate() {
      Date currentTime = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateString = formatter.format(currentTime);
      //ParsePosition pos = new ParsePosition(8);
     // Date currentTime_2 = formatter.parse(dateString, pos);
     // return currentTime_2;
      return dateString;
   }

     /** 
      * 去掉前面含有的0 
      * @param str 
      * @return 
      */  
     private static String leaveZero(String str){  
         if(str.length() == 2 && str.charAt(0) == '0'){  
             return str.substring(1,2);  
         }  
         return str;  
     }
       
     /** 
      * 获得文件的类型 
     *  @author ly 
     *  @date  2012-4-9 
      * @param file 文件的全路径 
      * @return 
      */  
     public static String getSuffix(String file) {  
         if(isEmpty(file) || file.indexOf(".") == -1 || file.indexOf(".") == file.length() - 1)  
             return "";  
         System.out.println(file.length());  
         System.out.println(file.lastIndexOf("."));  
         String suffix=file.substring(file.lastIndexOf("."),file.length());  
         return suffix.toLowerCase();  
     }  
       
       
     /** 
      * 邮箱校验 
      * @param email 
      * @return true：邮箱格式正确; false：邮箱格式错误 
      */  
     public static boolean isEmail(String email){       
         //String str = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//带横杠的出错  
         //String str = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";//带下划线出错  
 //      Pattern p = Pattern.compile(str);  
 //        Matcher m = p.matcher(email.trim());       
 //        return m.matches();   
          //Pattern pattern = Pattern.compile("[0-9a-zA-Z]*.[0-9a-zA-Z]*@[a-zA-Z]*.[a-zA-Z]*", Pattern.LITERAL);     
         if(email == null || "".equals(email)){
             return true;
         }    
         //验证开始     
         //不能有连续的.     
         if(email.indexOf("..") != -1){    
             return false;    
         }    
         //必须带有@     
         int atCharacter = email.indexOf("@");    
         if (atCharacter == -1) {    
             return false;    
         }    
         //最后一个.必须在@之后,且不能连续出现     
         if(atCharacter > email.lastIndexOf('.') || atCharacter+1 == email.lastIndexOf('.')){    
             return false;    
         }    
         //不能以.,@结束和开始     
         if (email.endsWith(".") || email.endsWith("@") || email.startsWith(".") || email.startsWith("@")) {    
             return false;    
         }    
         return true;    
   
     }  
     
     /** 
      * 手机号校验 
      * @param mobiles 
      * @return true：手机号格式正确; false:手机号格式不正确 
      */  
    public static boolean isMobileNO(String mobiles){       
         String str = "^1[3|4|5|6|7|8|9][0-9]{9}$";
         Pattern p = Pattern.compile(str);       
         Matcher m = p.matcher(mobiles.trim());       
         return m.matches();       
     }
     
     /** 
      * 电话号码验证 
      * @param  str 
      * @return 验证通过返回true 
      */  
     public static boolean isPhone(final String str) {  
         Pattern p1 = null, p2 = null;  
         Matcher m = null;  
         boolean b = false;  
         p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
         p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
         if (str.length() > 9) {  
            m = p1.matcher(str);  
            b = m.matches();  
         } else {  
             m = p2.matcher(str);  
            b = m.matches();  
         }  
         return b;  
     }  
     
     /**
      * 去除字符串中的特殊字符
      * @param str
      * @return
      */
     public static String replaceBlank(String str) {  
         String dest = "";  
         if (str!=null) {  
             Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
             Matcher m = p.matcher(str);  
             dest = m.replaceAll("");  
         }  
         return dest;  
     }
     
     /**
      * 针对包含英文的处理，保留空格
      * @param str
      * @return
      */
     public static String replaceEnglishBlank(String str) {  
         String dest = "";  
         if (str!=null) {  
             /*Pattern p = Pattern.compile("*|\t|\r|\n");  
             Matcher m = p.matcher(str);  
             dest = m.replaceAll("");  */
        	 dest = str.trim();
         }  
         return dest;  
     }
     
    public static boolean isCnorEn(char c) {
		if ((c >= 0x0391 && c <= 0xFFE5) // 中文字符
				|| (c >= 0x0000 && c <= 0x00FF)) // 英文字符
			return true;
		return false;
	}
     
	// GENERAL_PUNCTUATION 判断中文的“号
	// CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
	// HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
  
  
  
    public static boolean isChinese(String strName) {  
        char[] ch = strName.toCharArray();  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (isChinese(c) == true) {  
                //System.out.println(isChinese(c));  
                return true;  
            } else {  
                //System.out.println(isChinese(c));  
                return false;  
            }  
        }
		return false;  
    }  
   
    /**
     * 获取前几天时间
     * @param M_D M表示月，D表示天
     * @param offset 前几天，或几月  eg: 1 -1 正数表示以前，负数表示未来
     * @return
     */
    public static String preDate(String M_D,int offset){
    	 Date date = new Date();//获取当前时间
    	 Calendar calendar = Calendar.getInstance();    
    	 calendar.setTime(date);    
    	// calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间    
    	if(M_D !=null && M_D.equals("M")){
    		calendar.add(Calendar.MONTH, -offset);//当前时间前去一个月，即一个月前的时间 
    	}else{
    		calendar.add(Calendar.DAY_OF_MONTH, -offset);
    	}
    	Date currentTime = calendar.getTime();//获取一年前的时间，或者一个月前的时间  ,或几天前的时间
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
	/**
	 * 判断字符串中是否仅包含字母数字和汉字 各种字符的unicode编码的范围：
	 * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]） 数字：[0x30,0x39]（或十进制[48, 57]）
	 * 小写字母：[0x61,0x7a]（或十进制[97, 122]） 大写字母：[0x41,0x5a]（或十进制[65, 90]）
	 */
	public static boolean isLetterDigitOrChinese(String str) {

		String regex = "(^[\u4e00-\u9fa5]{1}[\u4e00-\u9fa5\\.·。]{0,8}[\u4e00-\u9fa5]{1}$)|(^[a-zA-Z]{1}[a-zA-Z\\s]{0,8}[a-zA-Z]{1}$)";
		return str.matches(regex);
	}

	
	/**
	 * 判断结尾是否是市
	 * @param str
	 * @return
	 */
	public static boolean  isCity(String str){
		boolean end=str.endsWith("市");
		return end;
	}
	
	/**
	 * 判断结尾是否是省
	 * @param str
	 * @return
	 */
	public static boolean  isProvince(String str){
		boolean end=str.endsWith("省");
		return end;
	}
	
	
	/**
	 * 身份证校验
	 * @param IDNumber
	 * @return
	 */
    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾
        boolean matches = IDNumber.matches(regularExpression);
        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() + 
                                "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }

        }
        return matches;
    }
 
       
 }  

