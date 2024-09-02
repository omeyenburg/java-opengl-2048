package org.main.graphics;

import org.main.geometry.*;
import org.main.graphics.shader.*;

import org.lwjgl.glfw.*; // https://javadoc.lwjgl.org/org/lwjgl/glfw/package-summary.html
import org.lwjgl.opengl.*; // https://javadoc.lwjgl.org/org/lwjgl/opengl/package-summary.html
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private long window;
    public Shader shader;
    private final Buffer buffer;
    private final Textures textures;
    private float time;
    private float delta_time;

    public Window(String title) {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create window
        create_window(title);

        // Set up shader
        shader = new Shader("/shader/vertex.glsl", "/shader/fragment.glsl");

        // Create buffers
        buffer = new Buffer();

        // Load textures
        textures = new Textures("/textures/sprites.png", "/font/font.png");

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        time = 0.0f;
        delta_time = 1.0f;
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

    private void create_window(String title) {
        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE); // Forward compatible, required on macOS

        // Create the window
        window = glfwCreateWindow(300, 300, title, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (local_window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(local_window, true); // We will detect this in the rendering loop

            }});

        // Get window size
        int[] screen_width = new int[]{0};
        int[] screen_height = new int[]{0};
        glfwGetWindowSize(window, screen_width, screen_height);

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert vidmode != null;

        // Center the window
        glfwSetWindowPos(
            window,
            (vidmode.width() - screen_width[0]) / 2,
            (vidmode.height() - screen_height[0]) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Retrieve context for lwjgl
        GL.createCapabilities();
    }

    public void update() {
        // Manage events
        glfwPollEvents();

        float current_time = (float) glfwGetTime();
        delta_time = current_time - time;
        time = current_time;

        // Background draw call for testing!
        buffer.add_instance(
            new Vec2(1.0f, 1.0f),
            new Vec4(0.0f, 0.0f, 1.0f, 1.0f),
            new Vec4(1.0f, 0.0f, 0.0f, 0.0f)
        );

        // Bind textures
        GL20.glActiveTexture(GL20.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures.tex_sprites);

        GL20.glActiveTexture(GL20.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures.tex_font);

        // Update shader variables
        shader.update();

        // Draw
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        GL33.glDrawElementsInstanced(
            GL11.GL_TRIANGLES,
            6,
            GL11.GL_UNSIGNED_INT,
            0L,
            buffer.index
        );

        // Update display
        glfwSwapBuffers(window);
        buffer.index = 0;
    }

    public boolean running() {
        return !glfwWindowShouldClose(window);
    }

    public float getTime() {
        return time;
    }

    public float getDeltaTime() {
        return delta_time;
    }
}