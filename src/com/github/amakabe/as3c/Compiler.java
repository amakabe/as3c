package com.github.amakabe.as3c;

import flex2.tools.oem.Application;
import flex2.tools.oem.Report;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


class Compiler
{
  private Application               app;
  private ArrayList<WatchableFile>  relatedFiles;

  public Compiler(File tgtFile, File outFile) throws FileNotFoundException
    {
      app = new Application(tgtFile);
      app.setOutput(outFile);
      relatedFiles = new ArrayList<WatchableFile>();
      relatedFiles.add(new WatchableFile(tgtFile.getPath()));
    }

  public void compile() throws IOException
    {
      PrintLogger logger = new PrintLogger();
      app.setLogger(logger);
      Printer.print(Printer.GREEN,"Compile...");
      if (app.build(true) > 0) {
        Printer.print(Printer.GREEN,"Compile SUCCESSFUL\n");
        relatedFiles = new ArrayList<WatchableFile>();
        Report report = app.getReport();
        mergeDepends(report.getSourceNames(Report.COMPILER));
        mergeDepends(report.getAssetNames(Report.COMPILER));
      }
      else {
        Printer.print(Printer.GREEN,"Compile FAILED\n");
        mergeDepends(logger.getSourceNames());
      }
    }

  public boolean isOutdated()
    {
      int n = relatedFiles.size();
      for (int i=0; i<n; i++) {
        if (relatedFiles.get(i).isOutdated())
          return true;
      }
      return false;
    }

  private void mergeDepends(String[] files)
    {
      int i, n;
      if (files == null)
        return;

      for (i=0, n=files.length; i<n; i++) {
        WatchableFile file = new WatchableFile(files[i]);
        if (! relatedFiles.contains(file))
          relatedFiles.add(file);
      }
    }
}

