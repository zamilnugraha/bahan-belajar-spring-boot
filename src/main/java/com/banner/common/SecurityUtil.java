package com.banner.common;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SecurityUtil {
	
	public static final String urlLocal      = "";

	public static final Integer redisStatus = 1; //0=mati 1=nyala
	
	public static boolean check(String appId, String appType, String appKey, String remoteAddress, String token) {
		System.out.println("ip: " + remoteAddress);	
		if(appId != null && appType != null && token != null) {
			if(appType.equalsIgnoreCase("web")) {
				if(remoteAddress == null) {
					return false;
				}else{
					if(appKey == null || appKey.equalsIgnoreCase("0")) {
						return false;
					}
				}
			} else {
				if(appType.equalsIgnoreCase("mobile")) {
					if(appId == null || appId.equalsIgnoreCase("0")) {
						return false;
					}
				} else {
					if(appType.equalsIgnoreCase("desktop")) {
						if(appKey == null || appKey.equalsIgnoreCase("0")) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
			//TOKEN CHECKED
			if(token != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}	
	}
	
	public static String rowTimeUpdate(Date tgl){
		String timeUpdate = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Bangkok"));
            timeUpdate = sdf.format(tgl);
		}catch(Exception e){
			System.out.println("Error Row Time Update: "+e.getMessage());
			e.printStackTrace();
		}
		return timeUpdate;
	}
	
	public static String generateID(){
        String saltStr    = "";
        Integer len = 20;
        try {
            
            final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            SecureRandom rnd = new SecureRandom();
            
            StringBuilder salt = new StringBuilder(len);
            while (salt.length()<len){
                int index = (int) (rnd.nextFloat()*AB.length());
                salt.append(AB.charAt(index));
            }
            saltStr = salt.toString();
        }catch(Exception e){
            System.out.println("Error Generate ID : "+e.getMessage());
            e.printStackTrace();
        }
        return saltStr;
    }
	
	public static String createdDate(){
		SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    sdfFormat.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Bangkok"));
	    String createDate = sdfFormat.format(new Date());
	    return createDate;
	}
	
	//UUID
	public static String uuidGenerate(){
		return UUID.randomUUID().toString();
	}
	
	//SECURE RANDOM
	public static String uuidSecureRandom(){
		//initialization of the application
		byte[] result = null;
		try{
			 SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

			//generate a random number
			 String randomNum = new Integer(prng.nextInt()).toString();

			//get its digest
			 MessageDigest sha = MessageDigest.getInstance("SHA-1");
			 result =  sha.digest(randomNum.getBytes());
			 
			 System.out.println("Random number: " + randomNum);
			 System.out.println("Message digest: " + new String(result));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new String(result);
	}
	
	public static String uuidGenerateID(){
		String s = "";
        double d;
        for (int i = 1; i <= 20; i++) {
            d = Math.random() * 10;
            s = s + ((int)d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }
        System.out.println("s: "+s);
        return s;
	}

}
