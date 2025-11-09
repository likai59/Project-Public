package com.gtalent.demo.util;

import org.python.util.PythonInterpreter;

public class JavaPythonRun {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a = 'hello world'");
        interpreter.exec("print a");
    }
}
