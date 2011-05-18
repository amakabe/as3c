package com.github.amakabe.as3c;

import java.io.File;


public class AS3C
{
  final static private String USAGE =
    "Usage: java com.github.amakabe.as3c [options] source-file\n" +
    "OPTIONS\n" +
    " -l PATH  flashlog.txt path (default: none)\n" +
    " -o PATH  outfile path (default: source-file =~ s/(\\.[^\\.]*)?$/.swf/)\n";

  final static void usage()
    {
      System.err.println(USAGE);
      System.exit(1);
    }

  public static void main(String[] args)
    {
      int numArgs = args.length;
      if (numArgs % 2 == 0)
        usage();

      String tgt = args[numArgs - 1];
      File tgtFile = new File(tgt);
      File outFile = new File(tgt.replaceAll("(\\.[^\\.]*)?$","") + ".swf");
      File logFile = null;
      for (int i=0; i<numArgs - 1; i+=2) {
        String arg = args[i];
        if (arg.equals("-l"))
          logFile = new File(args[i + 1]);
        if (arg.equals("-o"))
          outFile = new File(args[i + 1]);
        else
          usage();
      }

      try {
        if (logFile != null) {
          TailTracer tracer = new TailTracer(logFile);
          tracer.start();
        }
        Compiler compiler = new Compiler(tgtFile,outFile);
        compiler.compile();
        while (true) {
          if (compiler.isOutdated())
            compiler.compile();
          Thread.sleep(50);
        }
      }
      catch (Exception e) {
        System.err.println(e.toString());
      }
    }
}


