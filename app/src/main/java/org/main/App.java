package org.main;

import org.main.graphics.Window;
import org.main.graphics.shader.UniformValue;
import org.main.graphics.shader.UniformVariable;

public class App {

	public String getGreeting() {
        return "Hello World!";
	}

	public static void main(String[] args) {
		Window window = new Window("2048");
		UniformVariable time_var = window.shader.addVar("time", UniformValue.FLOAT.from(0));

		while (window.running()) {
			time_var.set(UniformValue.FLOAT.from(window.getTime()));
			window.update();
		}

		window.quit();
	}

}
