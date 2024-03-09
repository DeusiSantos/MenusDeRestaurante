/************************************************************************/
/* file.: ObjectSerializableGeneric.java                                */                 
/* Autor.: Osvaldo Manuel Ramos                                         */
/* Data.: 17-dez-2005                                                   */
/* Num.: 2817                                                           */
/* Objectivo.: Criacao de Janela                            		    */
/* Descricao.:                                				            */
/************************************************************************/
package isptec.io;


import java.io.*;
import javax.swing.*;


public class StringBufferModelo extends ObjectSerializableGeneric
{
	private StringBuffer buffer;

	public StringBufferModelo(String name, int size)
	{
		buffer = null;
		
		int nl = name != null ? name.length() : 0;
		if ( name != null )
         	buffer = new StringBuffer( name );
		else
         	buffer = new StringBuffer( size );

      	buffer.setLength( size );

		for (int i = nl; i < size; i++ )
			buffer.setCharAt(i, ' ');
	}
	
	public StringBuffer getBuffer()
	{
		return buffer;
	}
	
	public StringBufferModelo(int size)
	{
		buffer = new StringBuffer( size );
		buffer.setLength( size );
		
		for (int i = 0; i < size; i++ )
			buffer.setCharAt(i, ' ');
	}
	
    public int compareToIgnoreCase(StringBufferModelo st)
    {
        return buffer.toString().compareToIgnoreCase(st.toString());
    }
    
    public int compareTo(StringBufferModelo st)
    {
        return buffer.toString().compareTo(st.toString());
    }
    
    @Override
	public long sizeof()
	{
		return 2 * buffer.length();
	}
	
    @Override
	public String toString()
	{
		return buffer.toString(); 
	}
	
	public String toStringEliminatingSpaces()
	{
		StringBuilder newBuffer = new StringBuilder(new String(buffer));
		int nl = newBuffer.length();
		
		char ch = 0;
		boolean change = true;
		for (int i = nl - 1; change && i >= 0; i--)
		{
			ch = newBuffer.charAt(i);
			if (Character.isWhitespace(ch) == true)
				newBuffer.setCharAt(i, '\0');
			else
				change = false;
		}
		return new String (newBuffer).trim();
	}
	
    @Override
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeChars( buffer.toString() );
		}
		catch (IOException ex)
		{
			String msg = "falha na escrita de uma frase no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
    @Override
	public void read(RandomAccessFile stream)
	{
		try
		{
			char name[] = new char[ buffer.length() ], temp;

			for ( int i = 0; i < name.length; i++ )
			{
				 temp = stream.readChar();
				 name[ i ] = temp;
			}
			buffer = new StringBuffer(new String( name ).replace( '\0', ' ' ));
			buffer.setLength(name.length);
		}
		catch (IOException ex)
		{
			String msg = "falha na leitura de uma frase no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public static String readString(RandomAccessFile stream, int tamanho)
	{
		StringBufferModelo buf = new StringBufferModelo(tamanho);
		buf.read ( stream );
		return buf.toString();
	}	

    public void write(RandomAccessFile file, String nome)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
