package com.github.amakabe.as3c;

import java.io.File;

class WatchableFile extends File
{
  private long postModified;

  public WatchableFile(String path)
    {
      super(path);
      postModified = lastModified();
    }

  public boolean isOutdated()
    {
      long post = postModified;
      postModified = lastModified();
      return post != postModified;
    }
}
