package ss15_exception;

import common.InputHelper;

import java.util.Scanner;

public class TriangleTest {

    public static void checkTriangle(double a, double b, double c) throws IllegalTriangleException{
        if(a <= 0 || b <= 0 || c <= 0){
            throw new IllegalTriangleException("Cạnh tam giác phải lớn hơn 0!");
        }

        if(a + b <= c || a + c <= b || b + c <= a){
            throw new IllegalTriangleException("Tổng hai cạnh phải lớn hơn cạnh còn lại!");
        }
    }

    public static void main(String[] args) {
        try {
            double a = InputHelper.inputDouble("Nhập cạnh a: ");
            double b = InputHelper.inputDouble("Nhập cạnh b: ");
            double c = InputHelper.inputDouble("Nhập cạnh c: ");

            checkTriangle(a, b, c);
            System.out.println("Ba cạnh tạo thành 1 tam giác hợp lệ");
        } catch (IllegalTriangleException e) {
            System.out.println("Lỗi: "+e.getMessage());
        }
    }
}
