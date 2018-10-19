package com.luolong.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
 
public class PrimaryGenerater {
 
    private static final String SERIAL_NUMBER = "XXX"; // 流水号格式
    private static PrimaryGenerater primaryGenerater = null;

    public PrimaryGenerater() {
    }

    
    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static PrimaryGenerater getInstance() {
        if (primaryGenerater == null) {
            synchronized (PrimaryGenerater.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new PrimaryGenerater();
                }
            }
        }
        return primaryGenerater;
    }
    /**
	 * 随机获取字符串
	 * 
	 * @param length
	 *            随机字符串长度
	 * 
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
		if (length <= 0) {
			return "";
		}
		char[] randomChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
				'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
		}
		return stringBuffer.toString();
	}

    /**
     * 生成下一个编号
     */
    public static String geneterNextNumber() {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
		for (int i = 0; i < 8; i++) {
			stringBuffer.append(Math.abs(random.nextInt(8)));
		}
        return stringBuffer.toString()+formatter.format(date);
    }
    /**
     * 
     * 获取UUID随机编码
     */
    public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
    
    /**
     * 返回随机字符串，同时包含数字、大小写字母
     * 
     * @param len
     *            字符串长度，不能小于3
     * @return String 随机字符串
     */
    public static String randomStr(int len) {
        if (len < 3) {
            throw new IllegalArgumentException("字符串长度不能小于3");
        }
        // 数组，用于存放随机字符
        char[] chArr = new char[len];
        // 为了保证必须包含数字、大小写字母
        chArr[0] = (char) ('0' + StdRandom.uniform(0, 10));
        chArr[1] = (char) ('A' + StdRandom.uniform(0, 26));
        chArr[2] = (char) ('a' + StdRandom.uniform(0, 26));

        char[] codes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z' };
        // charArr[3..len-1]随机生成codes中的字符
        for (int i = 3; i < len; i++) {
            chArr[i] = codes[StdRandom.uniform(0, codes.length)];
        }

        // 将数组chArr随机排序
        for (int i = 0; i < len; i++) {
            int r = i + StdRandom.uniform(len - i);
            char temp = chArr[i];
            chArr[i] = chArr[r];
            chArr[r] = temp;
        }

        return new String(chArr);
    }
	
    public static void main(String[] args) {
    	System.err.println(PrimaryGenerater.geneterNextNumber());
	}
}
