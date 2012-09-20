/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.encryptor;

import org.apache.commons.dbcp.BasicDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * 加密解密工具。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-10			Leaon				创建PasswordEncryptor.java。
 *
 */
public class PasswordEncryptor {
	
	public static void main(String[] args) {
		// 基本加密解密
		// BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		// textEncryptor.setPassword("root");
		// String newPassword = textEncryptor.encrypt("leaon");
		// System.out.println(newPassword);
//		System.out.println(decrypt(newPassword));

		// 标准加密解密
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("root");
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally set the
															// algorithm
		String encryptedText1 = encryptor
				.encrypt("oracle.jdbc.driver.OracleDriver");
		String encryptedText2 = encryptor
				.encrypt("jdbc:oracle:thin:@127.0.0.1:1521:iplat");
		String encryptedText3 = encryptor.encrypt("iplat");
		String encryptedText4 = encryptor.encrypt("leaon");
		
		String encryptedText5 = encryptor
				.encrypt("admin");
		
		String encryptedText6 = encryptor
				.encrypt("leaon");
		
		System.out.println(encryptor.decrypt(encryptedText1) + " : "
				+ encryptedText1);
		System.out.println(encryptor.decrypt(encryptedText2) + " : "
				+ encryptedText2);
		System.out.println(encryptor.decrypt(encryptedText3) + " : "
				+ encryptedText3);
		System.out.println(encryptor.decrypt(encryptedText4) + " : "
				+ encryptedText4);
		
		System.out.println(encryptor.decrypt(encryptedText5) + " : "
				+ encryptedText5);
		
		System.out.println(encryptor.decrypt(encryptedText5) + " : "
				+ encryptedText6);
		
		
		
		// 增强的解密解密
		// StrongTextEncryptor textEncryptor1 = new StrongTextEncryptor();
		// textEncryptor1.setPassword("root");
		// String myEncryptedText =
		// textEncryptor1.encrypt("jdbc:mysql://localhost:3306/test");
		// System.out.println(myEncryptedText);
		
		//基于池的加密解密
		// PooledPBEStringEncryptor encryptor2 = new PooledPBEStringEncryptor();
		// encryptor2.setPoolSize(4); // This would be a good value for a 4-core
		// system
		// encryptor2.setPassword("jasypt");
		// encryptor2.setAlgorithm("PBEWithMD5AndTripleDES");
		// String encryptedText2 =
		// encryptor2.encrypt("jdbc:mysql://localhost:3306/test");
		// System.out.println(encryptedText2);

	}
}