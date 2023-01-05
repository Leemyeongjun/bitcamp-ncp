package com.eomcs.oop.ex02.test;

//# 관련된 기능(메서드)을 묶어 분류하기
//1) 분류 전
//2) 메서드를 클래스로 묶어 분류하기
//3) 클래스 변수 도입
//4) 인스턴스 변수 도입
//5) 인스턴스 메서드 활용
//6) 패키지 멤버 클래스로 분리
//7) 클래스를 역할에 따라 패키지로 분류하기
//

public class CalculatorTest2 {

  static class Calculator {

    static int result = 0;

    static void plus(int a, int b) {
      result = a + b;
    }

    static void minus(int a, int b) {
      result = a - b;
    }

    static void multiple(int a, int b) {
      result = a * b;
    }

    static void divide(int a, int b) {
      result = a / b;
    }
  }

  public static void main(String[] args) {

    Calculator.plus(2, 3);
    Calculator.minus(Calculator.result, 1);
    Calculator.multiple(Calculator.result, 7);
    Calculator.divide(Calculator.result, 3);

    System.out.printf("result = %d\n", Calculator.result);
  }
}
