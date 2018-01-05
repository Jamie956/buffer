package base.demo;

public class Polymorphism {
	public static void main(String[] args) {
		Animal d = new Deer();
		d.look();

		Animal t = new Cat();
		t.look();
	}
}

abstract class Animal {
	public final void look() {
		eat();
		sleep();
		run();
	}

	public abstract void eat();

	public abstract void sleep();

	public abstract void run();
}

class Deer extends Animal {
	@Override
	public void eat() {
		System.out.println("deer eat");
	}

	@Override
	public void sleep() {
		System.out.println("deer sleep");
	}

	@Override
	public void run() {
		System.out.println("deer run");
	}
}

class Cat extends Animal {
	@Override
	public void eat() {
		System.out.println("cat eat");
	}

	@Override
	public void sleep() {
		System.out.println("cat sleep");
	}

	@Override
	public void run() {
		System.out.println("cat run");
	}
}
