package isptec.io;

import java.io.*;

public class MyFile extends File
{
  public MyFile(String f)
  {
    super(f);
  }
  public static boolean existe(String f)
  {
    File stream = new File(f);
    return ((true == stream.exists()) && (true == stream.isFile()));
  }
} 
