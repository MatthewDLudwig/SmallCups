import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Scanner;
						
public class Main {
	public static void main(String[] args) throws Exception {
		Robot robert = new Robot(); Thread.sleep(3000);
//		robert.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
//		robert.delay(60 * 1000);
//		robert.delay(60 * 1000);
//		robert.delay(60 * 1000);
//		robert.delay(60 * 1000);
//		robert.delay(60 * 1000);
//		robert.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
//		tabSaver(robert, "High", 8);
		gatherWood(robert);
	}
		
	public static void fillArray(String[] arr, int i, int k, String v) {
		for (; i < k; ++i) {
			arr[i] = v;
		}
	}
	
	public static void racing(Robot robert) throws Exception {
		for (int i = 0; i < 150; i++) {
			robert.keyPress(KeyEvent.VK_LEFT);
			Thread.sleep(28);
			robert.keyRelease(KeyEvent.VK_LEFT);
			Thread.sleep(15);
			robert.keyPress(KeyEvent.VK_RIGHT);
			Thread.sleep(28);
			robert.keyRelease(KeyEvent.VK_RIGHT);
		}
	}
	
	public static String getTextFromClipboard() throws Exception {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}
	
	//Saves your tabs one at a time to a file.
	public static void tabSaver(String passedPath, Robot robert, String name, int amount) throws Exception {
		File f = new File(passedPath);
		StringBuilder tabs = new StringBuilder();
		
		if (f.exists()) {
			Scanner input = new Scanner(new FileInputStream(f));
			
			while (input.hasNextLine()) {
				tabs.append(input.nextLine()).append("\n");
			} 
			
			input.close();
		}
	
		robert.mouseMove(300, 33);
		
		for (int i = 0; i < amount; ++i) {
			robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
			robert.keyPress(KeyEvent.VK_CONTROL);
			robert.keyPress(KeyEvent.VK_A);
			robert.keyRelease(KeyEvent.VK_CONTROL);
			robert.keyRelease(KeyEvent.VK_A);
	
			Thread.sleep(300);
			robert.keyPress(KeyEvent.VK_CONTROL);
			robert.keyPress(KeyEvent.VK_C);
			robert.keyRelease(KeyEvent.VK_CONTROL);
			robert.keyRelease(KeyEvent.VK_C);
	
			Thread.sleep(300);
			tabs.append(getTextFromClipboard()).append("\n");
			
			robert.keyPress(KeyEvent.VK_CONTROL);
			robert.keyPress(KeyEvent.VK_W);
			robert.keyRelease(KeyEvent.VK_CONTROL);
			robert.keyRelease(KeyEvent.VK_W);

			Thread.sleep(300);
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write(tabs.toString());
		writer.close();
	}
	
	public static void timedClicker(Robot robert, Calendar time) throws Exception {
		while (true) {
			Calendar c = Calendar.getInstance();
			
			if (c.after(time)) {
				robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				break;
			}
		}
	}
	
	public static void typeText(Robot r, String text) throws Exception {
		for (char c : text.toCharArray()) {
			pressKeys(r, KeyEvent.getExtendedKeyCodeForChar(c));
		}
	}
	
	public static void pressKeys(Robot r, int... keys) throws Exception {
		for (int key : keys) {
			r.keyPress(key);
		}
		
		Thread.sleep(10);
		
		for (int key : keys) {
			r.keyRelease(key);
		}
	}
	
	//Minecraft 
	public static void gatherStones(Robot robert) throws Exception {
		for (int i = 0; i < 500; ++i) {			
			robert.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
			robert.keyPress(KeyEvent.VK_SHIFT);
			Thread.sleep(1000000);
			robert.keyRelease(KeyEvent.VK_SHIFT);
			robert.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
		}
	}
	
	//Minecraft
	public static void gatherWood(Robot robert) throws Exception {
		for (int i = 0; i < 100; i++) {
			robert.mouseWheel(1);
			Thread.sleep(2*1000);
		}
	}
	
	public static void scrollingthing(Robot robert) throws Exception {
		int times = 0;
		robert.mouseWheel(-1000);
		
		while (times++ < 600) {
				Thread.sleep(250);
				robert.mouseMove(900, 500); Thread.sleep(10);
//				robert.mousePress(MouseEvent.BUTTON3_DOWN_MASK);
//				robert.mouseRelease(MouseEvent.BUTTON3_DOWN_MASK);
				robert.mouseMove(950, 550); Thread.sleep(10);
//				robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
//				robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//				Thread.sleep(500);
//				typeText(robert, "Page" + times);
//				pressKeys(robert, KeyEvent.VK_ENTER);
				Thread.sleep(250);
				robert.mouseWheel(13);
		}
		robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
		robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
		robert.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
		robert.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
	}
	
	//The original function
	public static void liker(Robot robert) throws Exception {
		for (int i = 0; i < 200; ++i) {
			robert.keyPress(KeyEvent.VK_L);
			robert.keyRelease(KeyEvent.VK_L);
			Thread.sleep(200);
			
			robert.keyPress(KeyEvent.VK_RIGHT);
			robert.keyRelease(KeyEvent.VK_RIGHT);
			Thread.sleep(200);
		}
	}

	public static void main(String[] args) {
		//Edit this as needed to call any functions you needed.  I would run this from Eclipse when needed to do things and add/delete code whenever.
	}
}
