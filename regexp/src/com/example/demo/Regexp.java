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
		
//		String pattern = "\\w";// \w һ�����ֻ���ĸ
//		String pattern = "\\w*";// \w* 0�����ϸ����ֻ���ĸ
		String pattern = "\\w+";// \w+ 1�����ϸ����ֻ���ĸ
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
		
//		String pattern = "\\w+\\s\\d+";// \s һ���ո�
//		String pattern = "\\w+\\s*\\d+";// \s* 0�����ϸ��ո�
//		String pattern = "\\w+\\s+\\d+";// \s+ 1�����ϸ��ո�
		String pattern = "\\w+\\s?\\d+";// \s? 0��1���ո�
		
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
		
//		String pattern = "\\D+";// \D ��ȥ����
//		String pattern = "\\W";// \W ��ȥ���ֺ���ĸ
//		String pattern = "[a-zA-Z0-9]";// һ��a-z����ĸ��A-Z����ĸ��0-9������
//		String pattern = "\\w{1}";// w{1} һ����ĸ������
//		String pattern = "\\w{1,2}";// \w{1,2} 1-2����ĸ������
//		String pattern = "\\w{1,3}";// \w{1,3} 1-3����ĸ������
//		String pattern = "\\w{1,7}";// \w{1,7} 1-7����ĸ������
		String pattern = "\\w{0,7}";// \w{0,7} 0-7����ĸ������
		
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
		
//		String pattern = "[kj]ava";// [kj]ava k �� j ����ava
		String pattern = "[^kj]ava";// [^kj]ava ���� k �� j ����ava
		
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
		
//		String pattern = ".[o][b].*";// .[o][b].* ��1�����⣬��2����o����3����b������0��������
//		String pattern = ".ob.*";// ͬ��
		String pattern = ".*";// .* 0������ ����
		
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
//		String pattern = "\\w+\\s\\'.+";// \w+\s\'.+ һ��������ĸ������ + һ���ո� + һ��' + һ����������
//		String pattern = "\\w+\\sa.+";// \w+\sa.+ һ��������ĸ������ + һ���ո� + һ����ĸa + һ����������
//		String pattern = "\\D+\\sa.+";// \D+\sa.+" 1������(�ų�����) + һ���ո� + һ����ĸa + һ����������
//		String pattern = "\\D+\\s.+";// \D+\s.+ 1������(�ų�����) + һ���ո� + һ����������
//		String pattern = "\\w+\\s?\\'?\\d{0,3}\\'?";// \w+\s?\'?\d{0,3}\'?  һ��������ĸ������ + 0��1���ո� + 0��1��' + 0-3������ + 0��1��'
//		String pattern = "\\w+(\\s\\')?\\d{0,3}\\'?";//ͬ��
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
		
		String pattern = ".+(\\d)$";// ���ֽ�β
		
		for(String s : list){
			if(s.matches(pattern)){
				System.out.println("MATCHES");
			}else{
				System.out.println("NOT MATCHES");
			}
		}
		
	}
	
}
