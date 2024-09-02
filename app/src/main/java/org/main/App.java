package org.main;

import org.main.graphics.Window;

public class App {

	public String getGreeting() {
        return "Hello World!";
	}

	public static void main(String[] args) {
		Window window = new Window();

		while (window.running()) {
			window.update();
		}

		window.quit();
	}

}
