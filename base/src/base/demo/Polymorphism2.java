package base.demo;

public class Polymorphism2 {
	public static void main(String[] args) {
		President p = new President();
		p.parent = new son();
		p.call();
	}
}

class President {
	public Parent parent;

	public void call() {
		parent.call();
	}
}

abstract class Parent {
	public abstract void call();
}

class son extends Parent {
	public void call() {
		System.out.println("son");
	}
}

class daughter extends Parent {
	public void call() {
		System.out.println("daughter");
	}
}
