package cn.swust.util;

import java.sql.Date;
import java.util.Random;

import cn.swust.config.Constant;
import cn.swust.socket.jdbc.DBoperate;
import io.jsonwebtoken.Claims;

import net.sourceforge.pinyin4j.PinyinHelper;  
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;  
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;  
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;  
  
public class Test {  
 
  

	public static void main(String[] args) throws Exception {
		// JwtUtil jwt = new JwtUtil();
		// String str = jwt.createJWT("1", "123456", Constant.JWT_TTL);
		// System.out.println(str.length());
		// Claims c = jwt.parseJWT(str);
		// System.out.println(c.toString());
		// System.out.println(genDoubleRandom());
		// Random r=new Random();
		// System.out.println(r.nextDouble()*50);
//		DBoperate.createlocationSql("chengdou");
//		DBoperate.createlocationSql("mianyang");
//		DBoperate.createlocationSql("deyang");
//		DBoperate.createlocationSql("guangan");
//		StringUtil.getPingYin("adf");
//		System.out.println(Integer.toString(10, 4));
		System.out.println(DBoperate.update("111"));

	}

	public static double genDoubleRandom() {
		Random random = new Random();
		// 产生一个[0,499]的double数值
		double num = Double.valueOf(Math.floor(random.nextDouble() * 500));
		return num;
	}

	private static String getRandom() {
		Random r = new Random();
		String s = r.nextDouble() * 50 + "";
		int i = 0;
		for (; i < s.length(); i++) {
			if (s.charAt(i) == '.')
				break;
		}
		return s.substring(0, i + 2);
	}
}
