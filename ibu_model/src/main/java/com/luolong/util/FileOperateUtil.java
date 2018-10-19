package com.luolong.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.luolong.enums.FileAbsolutePathEnum;
import com.luolong.enums.FileContentTypeEnum;
import com.luolong.util.dialect.SystemPropertiesUtil;


/**
 * 文件操作工具
 * 
 * @author will
 * 
 */
public class FileOperateUtil {
	// private static final String separator = File.separator;
	private static final String separator = "/";
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy"+ separator + "MM");
	private static final String txtFileAimEncoding="UTF-8";
	public static String saveFile(InputStream inputStream,
			FileAbsolutePathEnum pathEnum, String fileExt) throws IOException {
		String relativePath = generateNewFilePath(pathEnum, fileExt);

		writeFile(inputStream, pathEnum, relativePath);
		
		if(StringUtils.equalsIgnoreCase("txt", fileExt)){		
			
			File sourceFile = new File(SystemPropertiesUtil.get(pathEnum.getValue())+ relativePath);
			String sourceEncoding="";
			try {
				sourceEncoding = codeString(sourceFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!StringUtils.equalsIgnoreCase("UTF-8", StringUtils.trim(sourceEncoding))){
//				log.info("TXT文件编码不是UTF-8,则转换成UTF-8");
				
				String newRelativePath = generateNewFilePath(pathEnum, fileExt);
				File aimFile = new File(SystemPropertiesUtil.get(pathEnum.getValue())+ newRelativePath);
				if (!aimFile.exists())
					aimFile.getParentFile().mkdirs();
				
				tranFileEncoding(sourceFile, aimFile, sourceEncoding, txtFileAimEncoding);
				
				//删除旧文件
				sourceFile.delete();				
			
				return newRelativePath;
			}			
		}
		
		return relativePath;
	}

	private static void writeFile(InputStream inputStream,
			FileAbsolutePathEnum pathEnum, String relativePath)
			throws FileNotFoundException, IOException {
		FileOutputStream outputStream = null;
		File file = null;
		byte[] buffer = new byte[10240];// 10K
		int len;
		try {
			file = new File(SystemPropertiesUtil.get(pathEnum.getValue())
					+ relativePath);
			if (!file.exists())
				file.getParentFile().mkdirs();
			outputStream = new FileOutputStream(file);

			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
		} finally {
			try{
                if (outputStream != null) {
                	outputStream.flush();
                	outputStream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
			try{
                if (buffer != null)
                	buffer = null;
            }catch(Exception e){
                e.printStackTrace();
            }
		}
	}

	private synchronized static String generateNewFilePath(
			FileAbsolutePathEnum pathEnum, String fileExt) {
		StringBuffer relative = new StringBuffer();
		relative.append(separator).append(format.format(new Date()))
				.append(separator).append(UUID.randomUUID().toString())
				.append(".").append(StringUtils.lowerCase(fileExt));

//		log.debug("文件存放绝对路径是:" + SystemPropertiesUtil.get(pathEnum.getValue())
//				+ relative);
		return relative.toString();
	}
	
	public static String generateFilePath(FileAbsolutePathEnum pathEnum, String fileExt){
		return  generateNewFilePath(pathEnum,fileExt);
	}
	
	//上传之后不需要改变文件名字的
	public static String saveFile2(InputStream inputStream,
			FileAbsolutePathEnum pathEnum, String fileName,String versionA) throws IOException {
		String relativePath = generateNewFilePath2(pathEnum, fileName,versionA);
		writeFile(inputStream, pathEnum ,relativePath);
		
		return relativePath;
	}
	
	private synchronized static String generateNewFilePath2(
			FileAbsolutePathEnum pathEnum, String fileName,String versionA) {
		StringBuffer relative = new StringBuffer();
		relative.append(separator).append(versionA)
				.append(separator).append(fileName);

//		log.debug("文件存放绝对路径是:" + SystemPropertiesUtil.get(pathEnum.getValue())
//				+ relative);
		return relative.toString();
	}


	public static void readFile(OutputStream outputStream,
			FileAbsolutePathEnum pathEnum, String filePath) throws IOException {
		FileInputStream inputStream = null;
		byte[] buffer = new byte[10240];// 10K
		int len;
		try {
			//modify by qahe 20140304
			File newFile = new File(SystemPropertiesUtil.get(pathEnum.getValue()) + filePath);
			if(null ==newFile ||!newFile.exists()){
				return;
			}
			//
			inputStream = new FileInputStream(newFile);
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
			if (buffer != null)
				buffer = null;
		}

	}

	public static void deleteFilePhysical(FileAbsolutePathEnum pathEnum,
			String filePath){
		try{
			File file = new File(SystemPropertiesUtil.get(pathEnum.getValue())
					+ filePath);
			file.delete();
		}catch (Exception e) {
//			log.warn("删除文件时发生错误:",e);
		}
	}

	public static File getFile(FileAbsolutePathEnum pathEnum, String filePath)
			throws IOException {
		return new File(SystemPropertiesUtil.get(pathEnum.getValue())
				+ filePath);
	}

	public static String getFileExt(String fileName) {
		if (StringUtils.isBlank(fileName) || !fileName.contains("."))
            return null;
        return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	public static String getFileExtOther(String fileName) {
	    if (StringUtils.isBlank(fileName) || !fileName.contains("."))
	        return "";
        return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }

	public static String getContentType(String fileName) {
		String ext = getFileExt(fileName);
		if (StringUtils.isBlank(ext)) {
//			log.info(fileName + "文件无后缀,采用默认zip方式处理!");
			return FileContentTypeEnum.zip.toString();// 默认设为zip
		} else {
			try {
				return FileContentTypeEnum.valueOf(ext.toLowerCase())
						.toString();
			} catch (Exception e) {
//				log.warn("后缀为:" + ext + "类型的文件当前系统不能处理,请添加!,默认将以zip的方式处理");
				return FileContentTypeEnum.zip.toString();// 默认设为zip
			}
		}
	}

	public static String getFileName(String filePath) {
		if (StringUtils.isBlank(filePath))
			return null;
		if (filePath.indexOf("/") == -1 && filePath.indexOf("\\") == -1) {
			return filePath;
		} else {
			if (filePath.indexOf("/") != -1) {
				return filePath.substring(filePath.lastIndexOf("/") + 1);
			} else {
				return filePath.substring(filePath.lastIndexOf("\\") + 1);
			}
		}
	}

	public static int getTxtFileCharSize(FileAbsolutePathEnum pathEnum,
			String filePath) {
		if (!StringUtils.equalsIgnoreCase("txt", getFileExt(filePath))) {
			return 0;
		}
		try {
			return getTxtFileCharSize(getFile(pathEnum, filePath));
		} catch (IOException e) {
			return 0;
		}
	}

	public static int getTxtFileCharSize(File textFile) {
		BufferedReader bufferedReader = null;
		CharBuffer charBuffer = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(textFile));
			charBuffer = CharBuffer.allocate(1000);

			int len = 0;
			int sumCount = 0;
			while ((len = bufferedReader.read(charBuffer)) != -1) {
				charBuffer.rewind();
				sumCount += len;
			}
			return sumCount;

		} catch (IOException e) {
			return 0;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;

				}
			} catch (IOException e) {}

			if (charBuffer != null) {
				charBuffer.clear();
				charBuffer = null;
			}
		}
	}
	/**
	 * 转换文件编码
	 * @param srcFile 源文件 
	 * @param destFile 目的文件 
	 * @param srcEncoding 源编码
	 * @param aimEncoding 目标编码
	 * @throws IOException
	 */
	public static void tranFileEncoding(File srcFile, File destFile,
			String srcEncoding, String aimEncoding) throws IOException {
//		log.info(srcEncoding+"-->"+aimEncoding);
		String line_separator = System.getProperty("line.separator");
		
		FileInputStream fis = null;
		DataInputStream in = null;
		BufferedReader bfr = null;
		Writer ow = null;
		FileOutputStream fos = null;
		
		try{
			fis = new FileInputStream(srcFile);
			
			in = new DataInputStream(fis);
			bfr = new BufferedReader(new InputStreamReader(in,srcEncoding));
			
			
			fos = new FileOutputStream(destFile);
			ow = new OutputStreamWriter(fos,aimEncoding);
			
			String line = null;
			while ((line = bfr.readLine()) != null){				
				ow.append(line).append(line_separator);
			}
		}catch (IOException e) {
			throw e;
		}finally{
			if(bfr!=null)bfr.close();
			if(in!=null)in.close();
			if(fis!=null)fis.close();
			if(ow!=null)ow.close();
			if(fos!=null)fos.close();
		}
	}
	
	
	public synchronized static String saveKnowledgeAbsoluteName(InputStream inputStream,
			FileAbsolutePathEnum pathEnum, String fileExt, String companyCode) throws IOException {
		String relativePath = generateNewCompanyFilePath(pathEnum, fileExt, companyCode);
		StringBuffer relative = new StringBuffer();
		relative.append(separator).append(companyCode).append(separator).append(format.format(new Date()))
				.append(separator);
/*		relative.append(separator).append(companyCode).append(separator).append(format.format(new Date()))
		.append(separator).append(UUID.randomUUID().toString())
		.append(".").append(StringUtils.lowerCase(fileExt));
*/		
//		log.debug("文件存放绝对路径是:" + SystemPropertiesUtil.get(pathEnum.getValue())
//				+ relative);
		return SystemPropertiesUtil.get(pathEnum.getValue())+relative.toString();
//		writeFile(inputStream, pathEnum, relativePath);
//		
//		if(StringUtils.equalsIgnoreCase("txt", fileExt)){		
//			
//			File sourceFile = new File(SystemPropertiesUtil.get(pathEnum.getValue())+ relativePath);
//			System.err.println("old:"+sourceFile.getName());
//			String sourceEncoding = new FileCharsetDetector().guestFileEncoding(sourceFile);
//			
//			if(!StringUtils.equalsIgnoreCase("UTF-8", StringUtils.trim(sourceEncoding))){
//				log.info("TXT文件编码不是UTF-8,则转换成UTF-8");
//				
//				String newRelativePath = generateNewCompanyFilePath(pathEnum, fileExt,companyCode);
//				File aimFile = new File(SystemPropertiesUtil.get(pathEnum.getValue())+ newRelativePath);
//				System.err.println("new:"+aimFile.getName());
//				if (!aimFile.exists())
//					aimFile.getParentFile().mkdirs();
//				tranFileEncoding(sourceFile, aimFile, sourceEncoding, txtFileAimEncoding);
//				//删除旧文件
//				sourceFile.delete();
//				System.err.println("delete over!");
//				return newRelativePath;
//			}			
//		}
//		
	}
	
	public synchronized static String createExportFileAbsoluteName(InputStream inputStream,
			FileAbsolutePathEnum pathEnum, String fileExt, String companyCode) throws IOException {
		StringBuffer relative = new StringBuffer();
		relative.append(separator).append(companyCode).append(separator).append("exportfile").append(separator).append(format.format(new Date()))
				.append(separator);
/*		relative.append(separator).append(companyCode).append(separator).append(format.format(new Date()))
		.append(separator).append(UUID.randomUUID().toString())
		.append(".").append(StringUtils.lowerCase(fileExt));
*/		
//		log.debug("文件存放绝对路径是:" + SystemPropertiesUtil.get(pathEnum.getValue())
//				+ relative);
		return SystemPropertiesUtil.get(pathEnum.getValue())+relative.toString();
	}
	
	
	
	public static String saveKnowledgeRelativeName(String absoluteName,
			FileAbsolutePathEnum pathEnum, String randomName) throws IOException {
		StringBuffer relative = new StringBuffer();
		int cutLength =SystemPropertiesUtil.get(pathEnum.getValue()).length();
		String tempName=absoluteName.substring(cutLength);
		relative.append(tempName).append(randomName);
 
		return relative.toString();
	}
	public static String createExportFileRelativeName(String absoluteName,
			FileAbsolutePathEnum pathEnum, String randomName) throws IOException {
		StringBuffer relative = new StringBuffer();
		int cutLength =SystemPropertiesUtil.get(pathEnum.getValue()).length();
		String tempName=absoluteName.substring(cutLength);
		relative.append(tempName).append(randomName);

		return relative.toString();
	}	
	private synchronized static String generateNewCompanyFilePath(
			FileAbsolutePathEnum pathEnum, String fileExt, String companyCode) {
		StringBuffer relative = new StringBuffer();
		relative.append(separator).append(companyCode).append(separator).append(format.format(new Date()))
				.append(separator);
/*		relative.append(separator).append(companyCode).append(separator).append(format.format(new Date()))
		.append(separator).append(UUID.randomUUID().toString())
		.append(".").append(StringUtils.lowerCase(fileExt));
*/		
//		log.debug("文件存放绝对路径是:" + SystemPropertiesUtil.get(pathEnum.getValue())
//				+ relative);
		return SystemPropertiesUtil.get(pathEnum.getValue())+relative.toString();
	}
	
    /**
     * 文件写入内容
     * @author: 研发部-陈薛树
     * @param filePath
     * @param content
     * @param append
    */
    public synchronized static boolean writeFile(String filePath,String content,boolean append){
        boolean flag = false;
        File file = new File(filePath);
        if(!file.exists()){
            file.getParentFile().mkdirs(); 
        }
        FileWriter fw = null;
        try{
            fw = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
            flag = true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return flag;
    }
    
	public static String toUtf8String(String s){  
	     StringBuffer sb = new StringBuffer();  
	       for (int i=0;i<s.length();i++){  
	          char c = s.charAt(i);  
	          if (c >= 0 && c <= 255){sb.append(c);}  
	        else{  
	        byte[] b;  
	         try { b = Character.toString(c).getBytes("utf-8");}  
	         catch (Exception ex) {  
	             System.out.println(ex);  
	                  b = new byte[0];  
	         }  
	            for (int j = 0; j < b.length; j++) {  
	             int k = b[j];  
	              if (k < 0) k += 256;  
	              sb.append("%" + Integer.toHexString(k).toUpperCase());  
	              }  
	     }  
	  }  
	  return sb.toString();  
	} 
	
	public static void main(String[] args) {
		// try {
		// InputStream inputStream = new FileInputStream(new File("spy.log"));
		//
		// String relativePath =
		// saveFile(inputStream,FileAbsolutePathEnum.ICON_PATH,"JPG");
		//
		// System.out.println(relativePath);
		//
		// System.out.println(getFileExt("D:\\test\\13213.jpg"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// System.out.println(getContentType("test.ZIP2"));

//		System.out.println(getFileName("tesxt.jpg"));
//		System.out.println(getFileName(""));
//		System.out.println(getFileName("c:\\test\\t\\tesxt.jpg"));
//		System.out.println(getFileName("/add/ss/tesxt.jpg"));
//		System.out.println(getFileName("tesxt.jpg"));
		String aaaaa="/33333我阿萨帝aa.doc";
		int cutLength ="/33333".length();
		String truenAM =aaaaa.substring(cutLength);
		System.out.println(truenAM);
	}
	
	public static String codeString(File file) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
        int p = (bin.read() << 8) + bin.read();
        bin.close();
        String code = null;
 
        switch (p) {
        case 0xefbb:
            code = "UTF-8";
            break;
        case 0xfffe:
            code = "Unicode";
            break;
        case 0xfeff:
            code = "UTF-16BE";
            break;
        default:
            code = "GBK";
        }
 
        return code;
	}


}
