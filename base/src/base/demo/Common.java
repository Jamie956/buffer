package base.demo;

import java.io.*;
import java.text.*;
import java.util.*;

import org.junit.Test;

public class Common {

	@Test
	public void stringDemo() {
		String s1 = new String("abc");
		String s2 = "abc";
		String s3 = "abc";
		String s4 = "123";
		String s5 = s2 + "d";
		System.out.println(s1.hashCode() + "-" + s2.hashCode() + "-" + s3.hashCode() + "-" + s4.hashCode());// 96354--96354--96354--48690
		System.out.println(s1 == s2);// false
		System.out.println(s3 == s2);// true
		System.out.println(s2.hashCode() + "-" + s5.hashCode());// 96354-2987074

		System.out.println(String.format("Hello %d World", 123));// Hello 123
																	// World
		System.out.printf("Hello %d World\n", 456);// Hello 456 World
		System.out.println(Arrays.toString("hello".getBytes()));// [104, 101,
																// 108, 108,
																// 111]
		char[] c = new char[8];
		"hello".getChars(1, 4, c, 3);// (int srcBegin, int srcEnd, char[] dst,
										// int dstBegin)
		System.out.println(Arrays.toString(c));// [?,?,?,e,1,1,?,?]
		System.out.println("helloworld".replace("o", "*"));// hell*w*rld
		System.out.println(Arrays.toString("tom,18,男,美国".split(",")));// [tom,
																		// 18,
																		// 男,
																		// 美国]
		System.out.println(Arrays.toString("123abc456def".split("")));// [1, 2,
																		// 3, a,
																		// b, c,
																		// 4, 5,
																		// 6, d,
																		// e, f]

		String file = "hellow.txtworld.txt";
		System.out.println(file.lastIndexOf("."));// 15
		System.out.println(file.substring(file.lastIndexOf(".")));// .txt
		System.out.println(file.substring(8, 11));// xtw
		System.out.println("     ox    1  ".trim());// zjm 1
		System.out.println(Arrays.toString("abc虾米123".toCharArray()));// [a, b,
																		// c, 虾,
																		// 米, 1,
																		// 2, 3]

		String str = "abcd1234";
		System.out.println(new StringBuffer(str).reverse());// 4321dcba

		StringBuffer sb = new StringBuffer();
		sb.append("a");
		sb.append("b");
		sb.append("c");
		sb.append("d");
		sb.append("e");
		System.out.println(sb);// abcde
		sb.insert(2, "g");
		System.out.println(sb);// abgcde
	}

	@Test
	public void dateDemo() {
		Date now = new Date();
		System.out.println(now);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(now));

		try {
			System.out.println(df.parse("2018-01-06"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		System.out.println(System.currentTimeMillis());// 1515214825126
		System.out.println(new Date().getTime());// 1515214825126
	}

	@Test
	public void mathDemo() {
		System.out.println(Math.floor(1.8));// 1.0
		System.out.println(Math.ceil(1.2));// 2.0
		System.out.println(Math.round(1.4));// 1
		System.out.println(Math.round(1.5));// 2
		Random random = new Random();
		System.out.println(random.nextInt(10));// 1-10
		System.out.println(random.nextBoolean());// true or false
	}

	@Test
	public void collectionDemo() {
		Collection<String> c1 = new ArrayList<String>();
		c1.add("1");
		c1.add("2");
		c1.add("3");

		Collection<String> c2 = new ArrayList<String>();
		c1.add("a");
		c1.add("b");
		c1.add("c");

		c2.addAll(c1);

		System.out.println(c2);
		Iterator<String> iter = c2.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println(c2.size());
		System.out.println(c2.contains(new String("1")));
		System.out.println(c2.containsAll(c1));
		c2.remove(new String("1"));
		System.out.println(c2);
		c2.clear();
		System.out.println(c2.isEmpty());
	}

	@Test
	public void listDemo() {
		ArrayList<String> list1 = new ArrayList<String>();
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			list1.add(0, "awesome");
		}
		long end1 = System.currentTimeMillis();
		System.out.println("ArrayList insert: " + (end1 - start1));

		LinkedList<String> list2 = new LinkedList<String>();
		long start2 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			list2.add(0, "awesome");
		}
		long end2 = System.currentTimeMillis();
		System.out.println("LinkedList insert: " + (end2 - start2));

		long start3 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			int index = new Random().nextInt(10000);
			list1.get(index);
		}
		long end3 = System.currentTimeMillis();
		System.out.println("ArrayList query: " + (end3 - start3));

		long start4 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			int index = new Random().nextInt(10000);
			list2.get(index);
		}
		long end4 = System.currentTimeMillis();
		System.out.println("LinkedList query: " + (end4 - start4));

		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(5);
		list3.add(9);
		list3.add(3);
		list3.add(6);
		list3.add(7);
		list3.add(1);
		list3.add(4);
		System.out.println(list3);
		Collections.sort(list3);
		System.out.println(list3);
		System.out.println(Collections.binarySearch(list3, 7));
	}

	@Test
	public void mapDemo() {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("user", "tomcat");
		map1.put("pwd", "123456");

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("user", "dog");
		map2.put("pwd", "567");

		map1.putAll(map2);
		System.out.println(map1);
		System.out.println(map1.containsKey("user"));// true
		System.out.println(map1.containsValue("dog"));// true
		System.out.println(map1.equals(map2));// true
		System.out.println(map1.get("user"));
		System.out.println(map1.isEmpty());// false
		System.out.println(map1.size());
		System.out.println(map1.keySet());
		System.out.println(map1.values());
		Set<Map.Entry<String, String>> entrySet = map1.entrySet();
		for (Map.Entry<String, String> e : entrySet) {
			System.out.println(e.getKey() + " ->" + e.getValue());
		}
		for (String k : map1.keySet()) {
			System.out.println(k + " => " + map1.get(k));
		}
	}

	@Test
	public void fileDemo() throws IOException {
		String root = System.getProperty("user.dir");
		System.out.println(root);

		File mydir = new File(root + "/mydir");
		System.out.println(mydir);
		mydir.mkdir();
		System.out.println(mydir.isDirectory());// true
		System.out.println(mydir.exists());// true

		File myTxt = new File(mydir + "/mytxt.txt");
		myTxt.createNewFile();
		System.out.println(myTxt.isFile());// true
		System.out.println(myTxt.exists());// true

		mydir.renameTo(new File(root + "/mydir2"));
	}

	@Test
	public void ioDemo() throws IOException {
		String root = System.getProperty("user.dir");
		File source = new File(root + "/source.txt");
		File target = new File(root + "/target.txt");

		try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target);) {
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			System.out.println("Dnoe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
