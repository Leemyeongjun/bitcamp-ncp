package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalcServer {

  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행!");

    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결됨!");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while (true) {
      // 클라이언트가 보낸 문자열을 한 줄 읽을 때까지 리턴하지 않는다.
      String message = in.nextLine();
      System.out.println(message);

      if (message.equals("quit")) {
        break;
      }

      String[] values = message.split(" ");
      int a = Integer.parseInt(values[0]);
      String action = values[1];
      int b =Integer.parseInt(values[2]);
      int result = 0;

      switch (action) {
        case "+": result = a + b; break;
        case "-": result = a - b; break;
        case "*": result = a * b; break;
        case "/": result = a / b; break;
      }

      out.println(result);
    }

    socket.close();
    serverSocket.close();

    System.out.println("서버 종료!");
  }
}
