package base.demo;

public class LazySingleton {
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);// true, This is a same Object
	}
}

class Singleton {
	private static Singleton instance = null;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (null == instance) {
			instance = new Singleton();
		}
		return instance;
	}
}
