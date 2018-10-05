package com.anatolyguz.uproxip;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;


/**
 *  Імпорт даних з Бігбразера до UPROX IP
 *
 *
 * перелік команд http://localhost:40001/json/help:
 * (замість localhost  - реальна ip адреса сервера)
 * (http://l192.168.1.78:40001/json/help)
 *

*  Про логін пароль - вони передаються з командного рядка.
*   для тесування, 
 *  Цитата звідси:
 *  http://u-prox.com/files/API/AcsWebService.pdf
 *  PasswordHash = MD5( MD5( MD5(пароль пользователя) + “F593B01C562548C6B7A31B30884BDE53”))
 *  Функция MD5(…) должна возвращать значение стандартной хэшфункции
 *  MD5 в виде шестнадцатеричной записи с использованием
 *  десятичных цифр и символов верхнего регистра: 0..9, A..F.
 *  
 *  
 *   После установки web-сервиса создается пользователь admin с паролем admin, 
 *   которому соответствует значение хэш-функции 
 *   88020F057FE7287D8D57494382356F97
 */
 



public class App 
{
	// Глобальні змінні, які доступні для всього проекту
	public static  final String 	SRV = "http://192.168.1.78:40001/json/";
	public static  final String 	namefileaccess = "/home/user/BB_Main.mdb";
	
		
	public static String PASSWORDHASH;	
	public static  String USERNAME;
	
	///////
			//+ "Authenticate";
	
	public static void main( String[] args )
    {
		//для тесування,
		// логін
		//USERNAME = "admin";
		// хеш паролю
		//PASSWORDHASH = "88020F057FE7287D8D57494382356F97";
		// або так
		//PASSWORDHASH = md5(md5(md5("admin") + "F593B01C562548C6B7A31B30884BDE53" ));
		
		USERNAME = args[0];
		PASSWORDHASH = md5(md5(md5(args[1]) + "F593B01C562548C6B7A31B30884BDE53" ));
		


//		PASSWORDHASH = md5(md5(md5("admin") + "F593B01C562548C6B7A31B30884BDE53" ));

        
        
		try {
			
			// Проводиться підключення 
			Authenticate au = new Authenticate(USERNAME,PASSWORDHASH);
			String UserSID = Webserver.authenticate(au);

					
//			Спочатку відділи
//			Отримаємо список відділів з файлу access
			
			ArrayList <Department> depListfromFile = Access.getlistdepartment();
			
//			тепер отримаємо список відділів з веб сервісу
			Emploeegetlist em =new Emploeegetlist();
			em.UserSID = UserSID;
			Webserver.departmentgetall(em);
			
			for (Department e : depListfromFile) {
					System.out.println( "name="+e.name );
				
			}
		
			
			//System.out.println(au.UserName );
			
			

			
			//System.out.println(em.Limit );
			//System.out.println(em.StartToken );			
			
			
			Webserver.employeegetlist(em);
			Webserver.departmentgetall(em);
			
			
			Webserver.logout(UserSID);
			
			 
			System.out.println( "Hello World!" );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	// взято звідси
	// https://www.geeksforgeeks.org/md5-hash-in-java/
	// додано верхній регістр (return hashtext.toUpperCase();) 
	public static String md5(String input) {
		
	    try { 
	    	  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext.toUpperCase(); 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
		
		
	}
	
	
	 

	
}
