package base.demo;

public class YesFactory {
	public static void main(String[] args) {
		MainFrame mf1 = MainFrameFactory.creatMainFrame();
		for (int i = 0; i < 10; i++) {
			mf1.run();
		}
	}
}

class MainFrameFactory {
	public static MainFrame creatMainFrame() {
		MainFrame mf = new MainFrame();
		mf.gpu = "1050ti";
		mf.cpu = "1400";
		mf.hardDisk = "WD";
		mf.mainboard = "B350M";
		return mf;
	}
}

class MainFrame {
	public String gpu;
	public String cpu;
	public String hardDisk;
	public String mainboard;

	public void run() {
		System.out.println("MainFrame [gpu=" + gpu + ", cpu=" + cpu + ", hardDisk=" + hardDisk + ", mainboard=" + mainboard + "]");
	}
	
}
