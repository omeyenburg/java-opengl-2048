package org.main.graphics.shader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.lwjgl.opengl.*; // imports GL11, GL20, GL30, GL33; functions are spread across these versions

public class Shader {
    private final int program;
    private final ArrayList<UniformVariable> variables;

    public Shader(String vertex_file, String fragment_file) {
        program = GL20.glCreateProgram();
        variables = new ArrayList<>();

        String vertex_code = readShader(vertex_file);
        String fragment_code = readShader(fragment_file);

        int vertex_shader = attachShader(vertex_code, GL20.GL_VERTEX_SHADER);
        int fragment_shader = attachShader(fragment_code, GL20.GL_FRAGMENT_SHADER);

        GL20.glLinkProgram(program);
        GL20.glDeleteShader(vertex_shader);
        GL20.glDeleteShader(fragment_shader);

        if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) != 1) {
            System.out.println("Shader linking failed!");
            System.exit(0);
        }

    }

    public void update() {
        GL20.glUseProgram(program);

        for (UniformVariable variable : variables) {
            variable.update();
        }
    }

    public UniformVariable addVar(String name, UniformValue value, boolean send) {
        int location = GL20.glGetUniformLocation(program, name);
        UniformVariable variable = new UniformVariable(value, location, send);
        variables.add(variable);
        return variable;
    }

    public UniformVariable addVar(String name, UniformValue value) {
        int location = GL20.glGetUniformLocation(program, name);
        UniformVariable variable = new UniformVariable(value, location, true);
        variables.add(variable);
        return variable;
    }

    private int attachShader(String shader_code, int shader_type) {
        int id = GL20.glCreateShader(shader_type);

        GL20.glShaderSource(id, shader_code);
        GL20.glCompileShader(id);

        if (GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) != 1) {
            System.out.println("Shader compilation failed!");
            System.out.print(GL20.glGetShaderInfoLog(id));
            System.exit(0);
        }

        GL20.glAttachShader(program, id);
        return id;
    }

    private String readShader(String file) {
        InputStream inputStream = Shader.class.getResourceAsStream(file);

        if (inputStream == null) {
            System.err.println("File not found!");
            return "";
        }

        InputStreamReader stream_reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(stream_reader);
        return reader.lines().collect(Collectors.joining("\n"));
    }
}