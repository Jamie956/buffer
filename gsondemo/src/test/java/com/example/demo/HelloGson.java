package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.example.demo.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HelloGson {

	@Test
	public void test1() {
		Gson gson = new Gson();
		int i = gson.fromJson("100", int.class); // 100
		double d = gson.fromJson("\"99.99\"", double.class); // 99.99
		boolean b = gson.fromJson("true", boolean.class); // true
		String str = gson.fromJson("String", String.class); // String

		System.out.println(i);
		System.out.println(d);
		System.out.println(b);
		System.out.println(str);
	}

	@Test
	public void test2() {// pojo to json
		Gson gson = new Gson();
		User user = new User("tomcat", 24);
		String jsonObject = gson.toJson(user); // {"name":"tomcat","age":24}
		System.out.println(jsonObject);
	}

	@Test
	public void test3() {// json to pojo
		Gson gson = new Gson();
		String jsonString = "{\"name\":\"lala\",\"age\":24}";
		User user = gson.fromJson(jsonString, User.class);
		System.out.println(user.toString());
	}

	@Test
	public void test4() {// json to pojo ( accept multiple key )
		Gson gson = new Gson();
		String jsonString = "{\"name\":\"lala\",\"age\":24,\"email_address\":\"mm@gmail.com\"}";
		User user = gson.fromJson(jsonString, User.class);
		System.out.println(user.toString());
	}

	@Test
	public void test5() {// json to pojo ( accept last key )
		Gson gson = new Gson();
		String json = "{\"name\":\"kaka\",\"age\":24,\"emailAddress\":\"111\","
				+ "\"email\":\"222\",\"email_address\":\"333\"}";
		User user = gson.fromJson(json, User.class);
		System.out.println(user.emailAddress); // ikidou_3@example.com
	}

	@Test
	public void test6() {// arr to str[]
		Gson gson = new Gson();
		String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
		String[] strings = gson.fromJson(jsonArray, String[].class);
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
	}

	@Test
	public void test7() {// arr to list
		Gson gson = new Gson();
		String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
//		String[] strings = gson.fromJson(jsonArray, String[].class);
		List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
		System.out.println(stringList);
	}
	
	@Test
	public void test9() {//str json to map
		String strr = "{0=\"hello\",1=\"world\"}";
		java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {}.getType();
		Gson gson = new Gson();
		Map<Integer, String> map = gson.fromJson(strr, type);
		System.out.println(map.get("0"));
		System.out.println(map.get("1"));
	}
	
	@Test
	public void test10() {//str json to map
		String strr = "{0:\"hello\",1:\"world\"}";
		java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {}.getType();
		Gson gson = new Gson();
		Map<Integer, String> map = gson.fromJson(strr, type);
		System.out.println(map.get("0"));
		System.out.println(map.get("1"));
	}
	
}
