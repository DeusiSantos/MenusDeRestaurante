package isptec.io;

import isptec.Defs;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Title: ROF</p>
 * <p>
 * Description: Random Object Files</p>
 * <p>
 * Copyright: Copyright (c) 2003</p>
 * <p>
 * Company: Goncar Software</p>
 *
 * @author C�sar J. G. Cardoso
 * @version 1.0
 */
public class Sizeof implements Serializable
{

    public static long sizeof(RegistGeneric out) throws IOException
    {
        File f = new File(Defs.tmpFile);

        if (f.exists())
        {
            f.delete();
        }

        RandomAccessFile baos = new RandomAccessFile(Defs.tmpFile, "rw");
        out.write(baos);
        long sz = baos.length();
        baos.close();
        (new File(Defs.tmpFile)).delete();
        return sz;
    }

    public static long sizeof(String fileName)
    {

        RandomAccessFile baos;
        long sz = 0;
        try
        {
            baos = new RandomAccessFile(fileName, "rw");
            sz = baos.length();
            baos.close();
            (new File(fileName)).delete();

        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Sizeof.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Sizeof.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sz;
    }
}
