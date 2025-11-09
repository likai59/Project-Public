package com.gtalent.demo.util;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JavaPythonCall {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:\\Users\\likai\\Downloads\\demo\\demo\\src\\main\\resources\\python\\JavaPythonCall.py");

        PyFunction pyFunction = interpreter.get("mul", PyFunction.class);
        int x = 4, y = 5;
        PyObject pyObject = pyFunction.__call__(new PyInteger(x), new PyInteger(y));
        System.out.println("answer is: " + pyObject);
    }
}
