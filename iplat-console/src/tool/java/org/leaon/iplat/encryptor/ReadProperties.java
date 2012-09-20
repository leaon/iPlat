package org.leaon.iplat.encryptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

/**
 * 加密解密类
 * 
 * @author Administrator
 * 
 */
public class ReadProperties {

	public Properties getProperties(StandardPBEStringEncryptor encryptor)
			throws IOException {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("system.properties");
		Properties p = new EncryptableProperties(encryptor);
		p.load(in);
		return p;
	}

	public static void main(String[] args) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("root");

		try {
			Properties p = new ReadProperties().getProperties(encryptor);
			String pass = p.getProperty("password");
			System.out.println(pass);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}