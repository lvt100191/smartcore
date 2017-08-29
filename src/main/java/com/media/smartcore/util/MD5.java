package com.media.smartcore.util;

import java.security.MessageDigest;

//import org.apache.log4j.Logger;


/**
 * @author: Ngo Ngoc Toan - nntoan@vasc.com.vn
 */
public class MD5
{
//	static Logger logger = Logger.getLogger(MD5.class);
    public static String toDigest(String text)
    {
      String md5String = "";
      try
      {       
          MessageDigest algorithm = MessageDigest.getInstance("MD5");
          algorithm.reset();          
          algorithm.update(text.getBytes());          
          byte messageDigest[] = algorithm.digest();          
          StringBuffer hexString = new StringBuffer();
          for (int i=0;i<messageDigest.length;i++) {
          String hex = Integer.toHexString(0xFF & messageDigest[i]); 
          if(hex.length()==1)
          hexString.append('0');          
          hexString.append(hex);
          }               
          md5String = hexString.toString();        
      }
      catch (Exception e)
      {
//        logger.error(MD5.class, e);
    	  e.printStackTrace();
      }
      return md5String;
    }
    
    
    public static void main(String[] args) {
		String s = "abc";
		System.err.println(toDigest(s));
	}
}