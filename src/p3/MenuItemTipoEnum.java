/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package p3;

import isptec.listas.Listas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deusineusio
 */
public enum MenuItemTipoEnum
{
    CARNE("Carne"), PEIXE("Peixe"), MARISCO("Marisco"), LEGUME("Legume"), 
    FRUTA("Fruta"), BEBIDA("Bebida"), SOBREMESA("Sobremesa");
    
    private final String nome;
    
    private MenuItemTipoEnum(String nome)
    {
        this.nome = nome;
    }
    
    public static MenuItemTipoEnum enviarLerOpcaoEscolhida()
    {
        List<String> opcoes = getStringEnums();
        
        int opcao;
        opcao = Listas.enviarLerOpcaoEscolhida(opcoes);
        return fromInteger(opcao);
    }
     
    public static List<String> getStringEnums()
    {
        List<String> stringEnums = new ArrayList();
        for (MenuItemTipoEnum m : MenuItemTipoEnum.values())
        {
            stringEnums.add(m.nome);
        }
        return stringEnums;
    }
    
    public int toInteger()
    {
        return this.ordinal() + 1;
    }
    
    public static MenuItemTipoEnum fromInteger(int x)
    {
        for (MenuItemTipoEnum m : MenuItemTipoEnum.values())
        {
            if (m.toInteger() == x)
                return m;
        }
        return null;
    }
}
