/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p3;

import isptec.utils.Utils;
import java.util.Scanner;

/**
 *
 * @author Aires Veloso
 */
public class P3
{
    public static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MinhaExcecao
    {
        // TODO code application logic here
        enviarFormApresentacao();
    }

    private static void enviarFormApresentacao() throws MinhaExcecao
    {
        System.out.println("=============================================");
        System.out.println("Sistema de Gestão de Menus de Restaurante");
        System.out.println("=============================================");
        System.out.println("Este sotware foi desenvolvido com Esplendor por: Deusineusio Artur dos Santos\n"
            + "(Numero de Matrícula: 20201402) Pelo");
        System.out.println("Instituto Superior Politécnico de Tecnologia e Ciência (ISPTEC)");
        System.out.println("Orientação Suprema: Professor. Doutor: Aires Veloso");
        System.out.println("=============================================");
        System.out.println("Leis em Vigor:");
        System.out.println("1. Este software é destinado exclusivamente ao uso educacional no âmbito do ISPTEC.");
        System.out.println("2. É proibida qualquer forma de redistribuição, venda ou utilização comercial sem autorização expressa.");
        System.out.println("3. Modificações no código-fonte só podem ser realizadas para fins educacionais e não podem ser distribuídas.");
        System.out.println("4. Qualquer utilização fora do ambiente de aprendizagem do ISPTEC requer aprovação por escrito dos desenvolvedores.");
        System.out.println("5. Os desenvolvedores não são responsáveis por perdas ou danos resultantes do uso indevido do software.");
        System.out.println("=============================================");
        

        if (Utils.concorda())
        {
            MenuPrincipal.menuPrincipal();
        }
        else
        {
            Utils.exit("Programa terminado");
        }
    }

}
