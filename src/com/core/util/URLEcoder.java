package com.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLEcoder {
	public static String decode(String str){
		String str_new = null;
		try {
			str_new = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return str_new;
	}
}
