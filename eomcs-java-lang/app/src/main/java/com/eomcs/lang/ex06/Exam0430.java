package com.eomcs.lang.ex06;

// # 흐름 제어문 - for 중첩과 break
//
public class Exam0430 {
  public static void main(String[] args) {
    int num = 5;
    // for 문의 중첩
    for (int i = 0; i < num; i++) {
      for (int j = 1; j < num-i; j++) {
        System.out.print(" ");
      }

      for (int j = 0; j < i*2+1; j++) {
        System.out.print('*');
      }

      System.out.println();
    }
  }
}


