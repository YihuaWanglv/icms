package com.icms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChineseToSpell {

	private static final Logger logger = Logger.getLogger(ChineseToSpell.class);

	// 中文转换成拼音时必须排除掉的中文特殊字符
	private static List<String> exceptStringList = new ArrayList<String>();

	// 常用的地名拼音(一般是多音字时要指定)
	private static Map<String, String> commonPlaceWordSpellMap = new HashMap<String, String>();

	private static HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

	static {
	}

	/**
	 * 转换一个中文字符
	 * exceptStringList没有初始化,暂时不能用,
	 */
	private static String converterOneWord(String word)
	{
		String pinyinName = "";
		char[] nameChar = word.toCharArray();
		for (int i = 0; i < nameChar.length; i++)
		{
			if (nameChar[i] > 128
					&& !(exceptStringList.contains(new String(
							new char[] { nameChar[i] }))))
			{
				try
				{
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
					if (pinyinName.contains("u:") || pinyinName.contains("U:"))
					{
						pinyinName = pinyinName.replace("u:", "v").replace("U:", "V");
					}
				}
				catch (BadHanyuPinyinOutputFormatCombination e)
				{
					logger.error("中文转换成拼音时出错,转换字符:" + word);
					e.printStackTrace();
				}
			}
			else
			{
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * commonPlaceWordSpellMap未初始化,暂时不能用
	 * @Title: converLocationWords
	 * @Description: TODO
	 * @param chinese
	 * @return
	 * @return String
	 * @exception/throws
	 */
	private static String converLocationWords(String chinese)
	{
		for (String key : commonPlaceWordSpellMap.keySet())
		{
			if (chinese.contains(key))
			{
				String val = commonPlaceWordSpellMap.get(key);
				chinese = chinese.replaceAll(key, val);
			}
		}
		return chinese;

	}

	private static boolean isEmptyVal(String chinese)
	{
		return null == chinese || "".equals(chinese);
	}

	/**
	 * 拼音转换 首字母大写
	 * 
	 * @param chiness
	 * @return
	 */
	public static String cnToSpellWithFirstCharUpCase(String chinese)
	{
		if (isEmptyVal(chinese))
		{
			return "";
		}
		if (!isChinese(chinese))
		{
			return chinese;
		}
		chinese = converLocationWords(chinese);

		char[] nameChar = chinese.toCharArray();
		String str = "";
		for (int i = 0; i < nameChar.length; i++)
		{
			String temp = String.valueOf(nameChar[i]);
			temp = converterOneWord(temp);
			if (temp.length() > 1)
			{
				char firstChar = Character.toUpperCase(temp.toCharArray()[0]);
				temp = firstChar + temp.substring(1, temp.length());
			}
			str += temp;
		}
		return str;
	}

	/**
	 * 拼音转换 全部字母大写
	 * @param chinese
	 * @return
	 */
	public static String cnToSpellWithWithUpperCaseSpell(String chinese)
	{
		return cnToSpellWithFirstCharUpCase(chinese).toUpperCase();

	}

	/**
	 * 拼音转换 全部字母小写
	 * @param chinese
	 * @return
	 */
	public static String cnToSpellWithWithLowerCaseSpell(String chinese)
	{
		return cnToSpellWithFirstCharUpCase(chinese).toLowerCase();

	}

	/**
	 * 拼音转换 首字母大写,如果超过规定长度，则简写
	 * @param chinese
	 * @param fisrtCharUpCaseLimitLength
	 * @return
	 */
	public static String cnToSpellWithFirstCharUpCaseAndTooLongBridge(
			String chinese, int fisrtCharUpCaseLimitLength)
	{
		if (isEmptyVal(chinese))
		{
			return "";
		}
		if (isChinese(chinese))
		{
			StringBuffer values = new StringBuffer();
			if (chinese.length() > fisrtCharUpCaseLimitLength)
			{
				values.append(cnToSpellWithFirstCharUpCase(chinese.substring(0,
						fisrtCharUpCaseLimitLength)));
				values.append(cnToSpllWithAbridge(chinese
						.substring(fisrtCharUpCaseLimitLength)));

			}
			else
			{
				values.append(cnToSpellWithFirstCharUpCase(chinese));
			}
			return values.toString();
		}
		else
		{
			return chinese;
		}
	}

	/**
	 * 转换为汉语拼音第一个大写字母(如：东信-->DX)
	 * @param chinese
	 * @return
	 */
	private static String cnToSpllWithAbridge(String chinese)
	{

		char[] nameChar = chinese.toCharArray();
		StringBuffer bridge = new StringBuffer();

		for (char ch : nameChar)
		{
			char[] chars = new char[1];
			chars[0] = ch;
			bridge.append(converterOneWord(new String(chars)).toUpperCase()
					.substring(0, 1));

		}
		return bridge.toString();

	}

	private static boolean isChinese(String str)
	{
		boolean chinese = false;
		for (char ch : str.toCharArray())
		{
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);

			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

			|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

			|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

			|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

			|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

			|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
			{

				chinese = true;
			}
		}

		return chinese;

	}
}
