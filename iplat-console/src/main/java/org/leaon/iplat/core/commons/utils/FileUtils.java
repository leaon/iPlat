/**
 * Copyright © 2010 - ${year} Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;


/**
 * 文件操作工具类，用于文件存取等操作。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-11			Leaon				创建FileUtils.java。
 *
 */
public class FileUtils {

	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(FileUtils.class);

	/**
	 * 将目录拷贝到目标位置。
	 * 
	 * @param srcDirName
	 *            源目录。
	 * @param descDirName
	 *            目标目录。
	 * @return 如果拷贝成功，返回true；否则返回false。
	 */
// TODO: Parameter 'srcDirName' is not assigned and could be declared final
	public static boolean copyDirectory(String srcDirName, String descDirName) {
		return FileUtils.copyDirectoryCover(srcDirName, descDirName, false);
	}

	/**
	 * 拷贝目录到目标位置，并指定是否覆盖。
	 * 
	 * @param srcDirName
	 *            源目录。
	 * @param descDirName
	 *            目标目录。
	 * @param coverlay
	 *            是否覆盖，true：覆盖；false：不覆盖。
	 * @return
	 */
	public static boolean copyDirectoryCover(String srcDirName,
// TODO: Parameter 'coverlay' is not assigned and could be declared final
			String descDirName, boolean coverlay) {
// TODO: Local variable could be declared final
		File srcDir = new File(srcDirName);
		// 判断源目录是否存在。
		if (!srcDir.exists()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		// 判断源目录是否是一个目录。
		else if (!srcDir.isDirectory()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		// 源目录是否以文件分隔符结束。
		if (!descDirName.endsWith(File.separator)) {
			descDirName = descDirName + File.separator; // 如果不是，追加文件分隔符。
		}
// TODO: Local variable could be declared final
		File descDir = new File(descDirName);
		// 判断目标目录是否存在。
		if (descDir.exists()) {
			if (coverlay) {
				// 如果覆盖，则删除目标目录。
				if (!FileUtils.deleteFileOrDirctory(descDirName)) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
					return false;
				}
			} else {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return false;
			}
		} else {
			// 如果不存在，创建目标目录。
			if (!descDir.mkdirs()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return false;
			}

		}

		boolean flag = true;
		// 列出源目录文件，并拷贝到目标目录。
// TODO: Local variable could be declared final
		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 如果是文件，则对文件进行拷贝。
			if (files[i].isFile()) {
				flag = FileUtils.copyFile(files[i].getAbsolutePath(),
						descDirName + files[i].getName());
				if (!flag) {
					break;
				}
			}
			// 如果是目录，则拷贝目录。
			if (files[i].isDirectory()) {
				flag = FileUtils.copyDirectory(files[i].getAbsolutePath(),
						descDirName + files[i].getName());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		return true;

	}

	/**
	 * 拷贝文件为目标文件。
	 * 
	 * @param srcFileName
	 *            源文件名称。
	 * @param descFileName
	 *            目标文件名称。
	 * @return 如果拷贝成功，返回true；否则返回false。
	 */
// TODO: Parameter 'descFileName' is not assigned and could be declared final
	public static boolean copyFile(String srcFileName, String descFileName) {
		return FileUtils.copyFileCover(srcFileName, descFileName, false);
	}

	/**
	 * 拷贝文件为目标文件，并这顶是否覆盖目标文件。
	 * 
	 * @param srcFileName
	 *            源文件名称。
	 * @param descFileName
	 *            目标文件名称。
	 * @param coverlay
	 *            指定当目标文件存在时，是否覆盖，true为覆盖；false为不覆盖。
	 * @return 如果拷贝成功，返回true；否则返回false。
	 */
	public static boolean copyFileCover(String srcFileName,
// TODO: Parameter 'descFileName' is not assigned and could be declared final
			String descFileName, boolean coverlay) {
// TODO: Local variable could be declared final
		File srcFile = new File(srcFileName);
		// 判断源文件是否存在。
		if (!srcFile.exists()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		// 判断源文件是否是文件类型。
		else if (!srcFile.isFile()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
// TODO: Local variable could be declared final
		File descFile = new File(descFileName);
		// 判断目标文件是否存在。
		if (descFile.exists()) {
			// 如果目标文件存在，并且指定覆盖参数为true，则删除目标文件。
			if (coverlay) {
				if (!FileUtils.deleteFileOrDirctory(descFileName)) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
					return false;
				}
			} else {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return false;
			}
		} else {
			if (!descFile.getParentFile().exists()) {
				// 判断父目录是否存在 。
				// 创建父目录。
				if (!descFile.getParentFile().mkdirs()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
					return false;
				}
			}
		}

		// 开始写入文件内容。
		int readByte = 0;
		InputStream ins = null;
		OutputStream outs = null;
		try {
			ins = new FileInputStream(srcFile);
			outs = new FileOutputStream(descFile);
// TODO: Local variable could be declared final
			byte[] buf = new byte[1024];
			while ((readByte = ins.read(buf)) != -1) {
				outs.write(buf, 0, readByte);
			}
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			// 关闭文件输入输出流。
			if (outs != null) {
				try {
					outs.close();
				} catch (IOException oute) {
				}
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException ine) {
				}
			}
		}
	}

	/**
	 * 创建文件路径。
	 * 
	 * @param descDirName
	 *            目标文件路径。
	 * @return 如果创建成功，则返回true；否则返回false。
	 */
	public static boolean createDirectory(String descDirName) {
		if (!descDirName.endsWith(File.separator)) {
			descDirName = descDirName + File.separator;
		}
// TODO: Local variable could be declared final
		File descDir = new File(descDirName);
		if (descDir.exists()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		// 创建指定路径。
		if (descDir.mkdirs()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 创建文件。
	 * 
	 * @param descFileName
	 *            目标文件名称。
	 * @return 如果创建成功，则返回true；否则返回false。
	 */
// TODO: Parameter 'descFileName' is not assigned and could be declared final
	public static boolean createFile(String descFileName) {
// TODO: Local variable could be declared final
		File file = new File(descFileName);
		if (file.exists()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 创建父目录。
			if (!file.getParentFile().mkdirs()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return false;
			}
		}

		// 创建文件。
		try {
			if (file.createNewFile()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return true;
			} else {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 删除指定目录。
	 * 
	 * @param dirName
	 *            要删除的目录名称。
	 * @return 如果删除成功，则返回true；否则返回false。
	 */
	public static boolean deleteDirectory(String dirName) {
		if (!dirName.endsWith(File.separator)) {
			dirName = dirName + File.separator;
		}
// TODO: Local variable could be declared final
		File dirFile = new File(dirName);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		boolean flag = true;
		// 列出所有文件。
// TODO: Local variable could be declared final
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 判断当前文件是否是文件类型。
			if (files[i].isFile()) {
				flag = FileUtils.deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} else if (files[i].isDirectory()) {
				flag = FileUtils.deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		}
		// 删除根目录。
		if (dirFile.delete()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 删除指定文件。
	 * 
	 * @param dirName
	 *            要删除的文件名称。
	 * @return 如果删除成功，则返回true；否则返回false。
	 */
// TODO: Parameter 'fileName' is not assigned and could be declared final
	public static boolean deleteFile(String fileName) {
// TODO: Local variable could be declared final
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return true;
			} else {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 删除指定文件或目录。
	 * 
	 * @param dirName
	 *            要删除的文件名称。
	 * @return 如果删除成功，则返回true；否则返回false。
	 */
// TODO: Parameter 'fileName' is not assigned and could be declared final
	public static boolean deleteFileOrDirctory(String fileName) {
// TODO: Local variable could be declared final
		File file = new File(fileName);
		if (!file.exists()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return false;
		} else {
			if (file.isFile()) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
				return FileUtils.deleteFile(fileName);
			} else {
				return FileUtils.deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 获取文件的相对路径。
	 * 
	 * @param dirPath
	 *            基准路径。
	 * @param file
	 *            待获取文件。
	 * @return 返回一个基于基准路径的相对文件路径。
	 */
// TODO: Parameter 'file' is not assigned and could be declared final
	private static String getEntryName(String dirPath, File file) {
		if (!dirPath.endsWith(File.separator)) {
			dirPath = dirPath + File.separator;
		}
		String filePath = file.getAbsolutePath();
		// ����Ŀ¼��������entry���ֺ������"/"����ʾ����Ŀ¼��洢
		if (file.isDirectory()) {
			filePath += "/";
		}
// TODO: Local variable could be declared final
		int index = filePath.indexOf(dirPath);

		return filePath.substring(index + dirPath.length());
	}

	/**
	 * 以行为单位，将文件内容读取到<code>List</code>列表。
	 * 
	 * @param file
	 *            待读取文件。
	 * @return 返回包含文件内容的<code>List</code>列表。
	 */
// TODO: Parameter 'file' is not assigned and could be declared final
	public static List<String> readFileToList(File file) {
		BufferedReader reader = null;
// TODO: Local variable could be declared final
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	/**
	 * 根据指定的字符集编码，将文件内容读取到<code>List</code>列表。
	 * 
	 * @param file
	 *            待读取文件。
	 * @param encodeType
	 *            编码格式。
	 * @return 返回包含文件内容的<code>List</code>列表。
	 */
// TODO: Parameter 'file' is not assigned and could be declared final
	public static List<String> readFileToList(File file, String encodeType) {
		BufferedReader reader = null;
// TODO: Local variable could be declared final
		List<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), encodeType));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (!(tempString.charAt(0) >= 'a' && tempString.charAt(0) <= 'z')) {
					tempString = tempString.substring(1);
				}
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	/**
	 * 将<code>List</code>中的内容写入文件。
	 * 
	 * @param srcList
	 *            目标<code>List</code>。
	 * @param encodType
	 *            字符集编码。
	 * @param descFile
	 *            目标文件。
	 * @return
// TODO: Parameter 'srcList' is not assigned and could be declared final
	 */
// TODO: Parameter 'encodType' is not assigned and could be declared final
	public static boolean writeListToFile(List<String> srcList,
			String encodType, File descFile) {
		BufferedWriter writer = null;
// TODO: Local variable could be declared final
		List<String> list = new ArrayList<String>();
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(descFile), encodType));
			for (int i = 0; i < list.size(); i++) {
// TODO: Local variable could be declared final
				String content = list.get(i);
				writer.write(content);
				writer.newLine();
			}
			writer.close();
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 解压缩文件。
	 * 
	 * @param zipFileName
	 *            压缩文件名称。
	 * @param descFileName
	 *            目标位置。
// TODO: Parameter 'zipFileName' is not assigned and could be declared final
	 */
	public static void unZipFiles(String zipFileName, String descFileName) {
		if (!descFileName.endsWith(File.separator)) {
			descFileName = descFileName + File.separator;
		}
		try {
			// 创建压缩文件。
// TODO: Local variable could be declared final
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String descFileDir = null;
// TODO: Local variable could be declared final
			byte[] buf = new byte[4096];
			int readByte = 0;
			// 获取压缩文件内容。
// TODO: Local variable could be declared final
			Enumeration enums = zipFile.entries();
			// 遍历压缩文件。
			while (enums.hasMoreElements()) {
				entry = (ZipEntry) enums.nextElement();
				// 获取文件名称。
				entryName = entry.getName();
				descFileDir = descFileName + entryName;
				if (entry.isDirectory()) {
					// 如果是目录，则创建目录。
					new File(descFileDir).mkdirs();
					continue;
				} else {
					// 如果是文件，创建父目录。
					new File(descFileDir).getParentFile().mkdirs();
				}
// TODO: Local variable could be declared final
				File file = new File(descFileDir);
				// 创建文件输出流。
// TODO: Local variable could be declared final
				FileOutputStream fouts = new FileOutputStream(file);
				// 创建文件输入流。
// TODO: Local variable could be declared final
				InputStream ins = zipFile.getInputStream(entry);
				while ((readByte = ins.read(buf)) != -1) {
					fouts.write(buf, 0, readByte);
				}
				fouts.close();
				ins.close();
			}
		} catch (Exception e) {
		}

	}

	/**
	 * 向指定文件中写入内容。
	 * 
	 * @param file
	 *            待写入文件。
	 * @param content
	 *            文件内容。
	 * @param flag
	 *            是否追加，true：追加；false：覆盖。
	 */
// TODO: Parameter 'content' is not assigned and could be declared final
	public static void writeFile(File file, String content, Boolean flag) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
// TODO: Local variable could be declared final
			FileWriter writer = new FileWriter(file, flag);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 按照指定的字符集，向指定文件中写入内容。
	 * 
	 * @param file
	 *            待写入文件。
	 * @param content
	 *            文件内容。
	 * @param flag
	 *            是否追加，true：追加；false：覆盖。
	 * @param encodeType
	 *            字符集编码。
	 */
// TODO: Parameter 'content' is not assigned and could be declared final
	public static void writeFile(File file, String content, Boolean flag,
// TODO: Parameter 'encodeType' is not assigned and could be declared final
			String encodeType) {
		try {
// TODO: Local variable could be declared final
			FileOutputStream writerStream = new FileOutputStream(file, flag);
// TODO: Local variable could be declared final
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					writerStream, encodeType));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 将指定目录添加到压缩文件中。
	 * 
	 * @param dirPath
	 *            待压缩目录
	 * @param fileDir
	 *            压缩文件。
	 * @param zouts
	 *            压缩输出流。
	 */
// TODO: Parameter 'dirPath' is not assigned and could be declared final
	public static void zipDirectoryToZipFile(String dirPath, File fileDir,
// TODO: Parameter 'zouts' is not assigned and could be declared final
			ZipOutputStream zouts) {
		if (fileDir.isDirectory()) {
// TODO: Local variable could be declared final
			File[] files = fileDir.listFiles();
			// 判断文件大小。
			if (files.length == 0) {
				// 创建压缩实体。
// TODO: Local variable could be declared final
				ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
				try {
					zouts.putNextEntry(entry);
					zouts.closeEntry();
				} catch (Exception e) {
				}
				return;
			}

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					// 压缩文件。
					FileUtils.zipFile(dirPath, files[i], zouts);
				} else {
					// 压缩目录。
					FileUtils.zipDirectoryToZipFile(dirPath, files[i], zouts);
				}
			}
		}
	}

	/**
	 * 压缩文件。
	 * 
	 * @param dirPath
	 *            源路径。
	 * @param file
	 *            源文件。
	 * @param zouts
	 *            压缩输出流。
	 */
// TODO: Parameter 'dirPath' is not assigned and could be declared final
	public static void zipFile(String dirPath, File file, ZipOutputStream zouts) {
		FileInputStream fin = null;
		ZipEntry entry = null;
		// 创建缓冲区。
// TODO: Local variable could be declared final
		byte[] buf = new byte[4096];
		int readByte = 0;
		if (file.isFile()) {
			try {
				// 创建输入流。
				fin = new FileInputStream(file);
				// 创建压缩实体。
				entry = new ZipEntry(getEntryName(dirPath, file));
				// 将压缩实体添加到压缩流。
				zouts.putNextEntry(entry);
				// 开始压缩。
				while ((readByte = fin.read(buf)) != -1) {
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				fin.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 压缩文件。
	 * 
	 * @param srcDirName
	 *            源路径。
	 * @param fileName
	 *            源文件名称。
	 * @param descFileName
	 *            目标文件名称。
	 */
// TODO: Parameter 'srcDirName' is not assigned and could be declared final
	public static void zipFiles(String srcDirName, String fileName,
// TODO: Parameter 'descFileName' is not assigned and could be declared final
			String descFileName) {
		// 源路径是否存在。
		if (srcDirName == null) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return;
		}
// TODO: Local variable could be declared final
		File fileDir = new File(srcDirName);
		if (!fileDir.exists() || !fileDir.isDirectory()) {
			return;
		}
// TODO: Local variable could be declared final
		String dirPath = fileDir.getAbsolutePath();
// TODO: Local variable could be declared final
		File descFile = new File(descFileName);
		try {
// TODO: Local variable could be declared final
			ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(
					descFile));
			if ("*".equals(fileName) || "".equals(fileName)) {
				FileUtils.zipDirectoryToZipFile(dirPath, fileDir, zouts);
			} else {
// TODO: Local variable could be declared final
				File file = new File(fileDir, fileName);
				if (file.isFile()) {
					FileUtils.zipFile(dirPath, file, zouts);
				} else {
					FileUtils.zipDirectoryToZipFile(dirPath, file, zouts);
				}
			}
			zouts.close();
		} catch (Exception e) {
		}
	}
}