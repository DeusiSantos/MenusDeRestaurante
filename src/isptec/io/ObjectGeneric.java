/************************************************************************
 file.: ObjectGeneric.java                                                  
 Autor.: Osvaldo Manuel Ramos                                         
 Data.: 17-dez-2005                                                   
 Num.: 2817                                                           
 Objectivo.: Criacao de Janela                            
 Descricao.:                                
************************************************************************/
package isptec.io;

import java.io.*;
import javax.swing.*;

public abstract class ObjectGeneric implements DebugInterface, Serializable
{
	
	protected ObjectGeneric() { }
	
    @Override
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
}
