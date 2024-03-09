/************************************************************************/
/* file.: ObjectSerializableGeneric.java                                */                 
/* Autor.: Osvaldo Manuel Ramos                                         */
/* Data.: 17-dez-2005                                                   */
/* Num.: 2817                                                           */
/* Objectivo.: Criacao de Janela                            		*/
/* Descricao.:                                				*/
/************************************************************************/
package isptec.io;

import java.io.*;
import javax.swing.*;

public abstract class ObjectSerializableGeneric implements DebugInterface, RegistGeneric
{
	
	protected ObjectSerializableGeneric() { }
	
    @Override
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
	
    @Override
	public long sizeof() throws IOException
	{
		return Sizeof.sizeof(this);
	}
}
