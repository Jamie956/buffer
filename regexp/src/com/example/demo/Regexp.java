package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Regexp {
	
	@Test
	public void test1(){
		List<String> list = Arrays.asList
		(	
			"a",
			"1",
			"abc",
			"abcde987321",
			"abcdefg",
			"defghij",
			""
		);
		
//		String pattern = "\\w";// \w 一个数字或字母
//		String pattern = "\\w*";// \w* 0或以上个数字或字母
		String pattern = "\\w+";// \w+ 1或以上个数字或字母
//		String pattern = "abc\\w*";
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}

	@Test
	public void test2(){
		List<String> list = Arrays.asList
		(	
			"abc",
			"abc 123",
			"abc123",
			"abc  123"
		);
		
//		String pattern = "\\w+\\s\\d+";// \s 一个空格
//		String pattern = "\\w+\\s*\\d+";// \s* 0或以上个空格
//		String pattern = "\\w+\\s+\\d+";// \s+ 1或以上个空格
		String pattern = "\\w+\\s?\\d+";// \s? 0或1个空格
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
	@Test
	public void test3(){
		List<String> list = Arrays.asList
		(	
			"a",
			"rt",
			"C",
			"+",
			"",
			"RtS",
			"8",
			"abcdefg",
			"a+\\"
		);
		
//		String pattern = "\\D+";// \D 除去数字
//		String pattern = "\\W";// \W 除去数字和字母
//		String pattern = "[a-zA-Z0-9]";// 一个a-z的字母或A-Z的字母或0-9的数字
//		String pattern = "\\w{1}";// w{1} 一个字母或数字
//		String pattern = "\\w{1,2}";// \w{1,2} 1-2个字母或数字
//		String pattern = "\\w{1,3}";// \w{1,3} 1-3个字母或数字
//		String pattern = "\\w{1,7}";// \w{1,7} 1-7个字母或数字
		String pattern = "\\w{0,7}";// \w{0,7} 0-7个字母或数字
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
	@Test
	public void test4(){
		List<String> list = Arrays.asList
		(	
			"kava",
			"sava",
			"lava",
			"tava",
			"java"
		);
		
//		String pattern = "[kj]ava";// [kj]ava k 或 j 连接ava
		String pattern = "[^kj]ava";// [^kj]ava 除了 k 或 j 连接ava
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
	@Test
	public void test5(){
		List<String> list = Arrays.asList
		(	
			"Robert",
			"book",
			"cat",
			"job",
			"Todor",
			"+ob 123? @ p",
			"Boooob",
			"jjobss"
		);
		
//		String pattern = ".[o][b].*";// .[o][b].* 第1个任意，第2个是o，第3个是b，后面0或多个任意
//		String pattern = ".ob.*";// 同上
		String pattern = ".*";// .* 0个或多个 任意
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
	@Test
	public void test6(){
		List<String> list = Arrays.asList
		(	
			"abc135gf",
			"string '123'",
			"int a = 5247;",
			"int A = 5247;",
			"int b = 5247;",
			"1int a = 5247;",
			"string '"
		);
		
//		String pattern = "\\w+";
//		String pattern = "\\w+\\s\\'.+";// \w+\s\'.+ 一个或多个字母或数字 + 一个空格 + 一个' + 一个或多个任意
//		String pattern = "\\w+\\sa.+";// \w+\sa.+ 一个或多个字母或数字 + 一个空格 + 一个字母a + 一个或多个任意
//		String pattern = "\\D+\\sa.+";// \D+\sa.+" 1个或多个(排除数字) + 一个空格 + 一个字母a + 一个或多个任意
//		String pattern = "\\D+\\s.+";// \D+\s.+ 1个或多个(排除数字) + 一个空格 + 一个或多个任意
//		String pattern = "\\w+\\s?\\'?\\d{0,3}\\'?";// \w+\s?\'?\d{0,3}\'?  一个或多个字母或数字 + 0或1个空格 + 0或1个' + 0-3个数字 + 0或1个'
//		String pattern = "\\w+(\\s\\')?\\d{0,3}\\'?";//同上
		String pattern = "\\w+(\\s\\'(\\d*)\\')?";
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
	@Test
	public void test7(){
		List<String> list = Arrays.asList
		(	
			"Marko is a good boy.",
			"Our Marko, is a good boy!",
			"Nobody is as good as our Marko is!"
		);
		
//		String pattern = "M.+";
		String pattern = "^M.+";
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
	@Test
	public void test8(){
		List<String> list = Arrays.asList
		(	
			"2.345,56",
			"-52.678.110",
			"235",
			"128m"
		);
		
		String pattern = ".+(\\d)$";// 数字结尾
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
}
