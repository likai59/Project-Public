package com.gtalent.demo.util;

import org.python.util.PythonInterpreter;

public class JavaPythonFile {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:\\Users\\likai\\Downloads\\demo\\demo\\src\\main\\resources\\python\\JavaPyhonFile.py");
    }
}
