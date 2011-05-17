package com.github.amakabe.as3c;

class Printer
{
  static final String NONE   = "39";
  static final String BLACK  = "30";
  static final String RED    = "31";
  static final String GREEN  = "32";
  static final String YELLOW = "33";
  static final String BLUE   = "34";
  static final String PURPLE = "35";
  static final String CYAN   = "36";
  static final String WHITE  = "37";

  static synchronized void print(String color, String message)
    {
      System.out.println("\033[" + color + "m" + message + "\033[39m");
    }
}
