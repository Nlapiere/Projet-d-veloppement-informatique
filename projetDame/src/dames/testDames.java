package dames;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class testDames {
	public static void main(String[] args) {
		Plateau testBoard = new Plateau();
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testBoard.initMenu();
		testBoard.getPan().setVisible(true);
	}
}
