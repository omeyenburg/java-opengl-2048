package org.main.graphics.shader;

import java.util.ArrayList;
import org.lwjgl.opengl.*; // imports GL11, GL20, GL30, GL33; functions are spread across these versions

public class Shader {
    private final int program;
    private final ArrayList<UniformVariable> variables;

    public Shader(String vertex, String fragment) {
        program = GL20.glCreateProgram();
        variables = new ArrayList<>();

        int vertex_shader = attach_shader(vertex, GL20.GL_VERTEX_SHADER);
        int fragment_shader = attach_shader(fragment, GL20.GL_FRAGMENT_SHADER);

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

    public UniformVariable add_var(String name, UniformValue value, boolean send) {
        int location = GL20.glGetUniformLocation(program, name);
        UniformVariable variable = new UniformVariable(value, location, send);
        variables.add(variable);
        return variable;
    }

    public UniformVariable add_var(String name, UniformValue value) {
        int location = GL20.glGetUniformLocation(program, name);
        UniformVariable variable = new UniformVariable(value, location, true);
        variables.add(variable);
        return variable;
    }

    private int attach_shader(String shader_code, int shader_type) {
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
}