package com.github.amakabe.as3c;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


class TailTracer extends Thread
{
  private File           file;
  private long           lastLength;
  private BufferedReader reader;

  public TailTracer(final File targetFile) throws IOException, FileNotFoundException
    {
      file = targetFile;
      lastLength = file.length();
      open(lastLength);
    }

  public void run()
    {
      String line;
      while (true) {
        long length = file.length();
        while (length != lastLength) {
          try {
            if (length < lastLength) {
              reader.close();
              open(0);
            }
            while ((line = reader.readLine()) != null)
              Printer.print(Printer.NONE,line);
          }
          catch (IOException e) {
            Printer.print(Printer.RED,e.getMessage());
          }
          finally {
            lastLength = length;
          }
        }

        try {
          Thread.sleep(50);
        }
        catch (InterruptedException e) {
          Printer.print(Printer.CYAN, e.toString());
        }
      }
    }

  private void open(long pos) throws IOException
    {
      reader = new BufferedReader(new FileReader(file));
      if (pos > 0)
        reader.skip(pos);
    }
}

