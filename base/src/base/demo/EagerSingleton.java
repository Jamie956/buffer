package base.demo;

public class EagerSingleton {
	public static void main(String[] args) {
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		System.out.println(s1 == s2);// ture

	}
}

class Singleton2 {
	private static final Singleton2 instance2 = new Singleton2();// create Object when class load

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		return instance2;
	}
}
