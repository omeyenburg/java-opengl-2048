package org.main.graphics;

import org.lwjgl.opengl.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Textures {
    public int tex_sprites;
    public int tex_font;

    public Textures(String sprites_file, String font_file) {
        tex_sprites = loadTexture(sprites_file);
        tex_font = loadTexture(font_file);
    }

    private int loadTexture(String file) {
        BufferedImage image = loadImage(file);

        int width = image.getWidth();
        int height = image.getHeight();

        ByteBuffer buffer = imageToBuffer(image, width, height);
        return bufferToTexture(buffer, width, height);
    }

    private BufferedImage loadImage(String file) {
        try {
            InputStream stream = Textures.class.getResourceAsStream(file);
            return ImageIO.read(stream);
        } catch (IOException e) {
            System.out.println("The image was not loaded: " + file);
            System.exit(1);
        }
        return null;
    }

    private static ByteBuffer imageToBuffer(BufferedImage image, int width, int height) {
        // Retrieve pixels as ARGB
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        // Allocate a direct ByteBuffer to store the image data in RGBA format
        ByteBuffer buffer = ByteBuffer.allocateDirect(pixels.length * 4);
        for (int pixel : pixels) {
            buffer.put((byte) ((pixel >> 16) & 0xFF));  // Red value
            buffer.put((byte) ((pixel >> 8) & 0xFF));  // Green value
            buffer.put((byte) (pixel & 0xFF));  // Blue value
            buffer.put((byte) ((byte) (pixel >> 24) & 0xFF));  // Alpha value
        }

        // Prepare buffer for reading; does not flip the image
        buffer.flip();

        return buffer;
    }

    private static int bufferToTexture(ByteBuffer buffer, int width, int height) {
        // Generate Texture
        int id = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        // Specify texture parameters
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_BORDER);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_BORDER);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_R, GL20.GL_CLAMP_TO_BORDER);
        GL30.glTexParameterfv(GL11.GL_TEXTURE_2D, GL30.GL_TEXTURE_BORDER_COLOR, new float[]{0.0f, 0.0f, 0.0f, 0.0f});

        // Set the texture data
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        return id;
    }

}
