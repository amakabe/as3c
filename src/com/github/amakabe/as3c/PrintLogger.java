package com.github.amakabe.as3c;

import flex2.tools.oem.Logger;
import flex2.tools.oem.Message;
import java.util.ArrayList;


class PrintLogger implements Logger
{
  private ArrayList<String> sourceNames;

  public PrintLogger()
    {
      sourceNames = new ArrayList<String>();
    }

  public String[] getSourceNames()
    {
      String[] a = {};
      return sourceNames.toArray(a);
    }

  public void log(Message message, int errorCode, String source)
    {
      String lv = message.getLevel();
      String path = message.getPath();

      if (path != null && ! sourceNames.contains(path))
        sourceNames.add(path);

      if (lv == Message.ERROR) {
        Printer.print(
          Printer.RED,
          path + "(" + message.getLine() + ") " + errorCode + ": " + message
          );
      }
      else if (lv == Message.WARNING) {
        Printer.print(
          Printer.YELLOW,
          path + "(" + message.getLine() + ") " + errorCode + ": " + message
          );
      }
      else {
        Printer.print(Printer.BLUE,message.toString());
      }
    }
}

