package org.main.graphics;

import org.lwjgl.*; // https://javadoc.lwjgl.org/org/lwjgl/package-summary.html
import org.lwjgl.glfw.*; // https://javadoc.lwjgl.org/org/lwjgl/glfw/package-summary.html
import org.lwjgl.opengl.*; // https://javadoc.lwjgl.org/org/lwjgl/opengl/package-summary.html
import org.lwjgl.system.*; // https://javadoc.lwjgl.org/org/lwjgl/system/package-summary.html

import org.main.graphics.shader.Shader;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private long window;
    public Shader shader;
    private final Buffer buffer;

    public Window() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create window
        create_window();

        // Set up shader
        String vertex_shader = "/shader/vertex.glsl";
        String fragment_shader = "/shader/vertex.glsl";
        shader = new Shader(vertex_shader, fragment_shader);

        // Create buffers
        buffer = new Buffer();

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void quit() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.free();
        }
    }

    private void create_window() {
        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        //glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden until shown manually
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // Request OpenGL 3.3
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); // Use Core Profile
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE); // Forward compatible, required on macOS

        // Create the window
        window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (local_window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(local_window, true); // We will detect this in the rendering loop

            }});

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            assert vidmode != null;

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        //glfwShowWindow(window); // needed if glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); was set
    }

    public void update() {
        glfwPollEvents();

        shader.update();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        GL33.glDrawElementsInstanced(
                GL11.GL_TRIANGLES,
                6,
                GL11.GL_UNSIGNED_INT,
                0L,
                buffer.index
            );

        glfwSwapBuffers(window);
        buffer.index = 0;
    }

    public boolean running() {
        return !glfwWindowShouldClose(window);
    }
}