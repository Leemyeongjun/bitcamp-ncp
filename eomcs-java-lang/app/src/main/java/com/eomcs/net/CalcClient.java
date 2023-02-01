package com.eomcs.net;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);

    Socket socket = new Socket("192.168.0.241", 8888);
    System.out.println("서버에 연결되었음!");

    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());

    while (true) {
      System.out.print("입력> ");
      String message = keyScan.nextLine();
      out.println(message);

      if (message.equals("quit")) {
        break;
      }

      String response = in.nextLine();
      System.out.printf("응답: %s\n", response);
    }

    out.close();
    in.close();

    socket.close();

    System.out.println("클라이언트 종료!");
    keyScan.close();
  }
}
