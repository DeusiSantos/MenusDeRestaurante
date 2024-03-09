package p3;

import isptec.listas.Listas;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            MenuPrincipal.menuPrincipal();
        } catch (MinhaExcecao me) {
            System.err.println("Ocorreu uma MinhaExcecao: " + me.getMessage());
            me.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void menuPrincipal() throws MinhaExcecao {
        try {
            String[] opcoes = { "MenuRestaurante", "MenuItem", "MenuData","Defesa", "Sair" };
            int opcao;
            opcao = Listas.enviarLerOpcaoEscolhida(opcoes);
            switch (opcao) {
                case 1:
                    gerirMenuRestaurante();
                    break;
                case 2:
                    gerirMenuItem();
                    break;
                case 3:
                    gerirMenuData();
                    break;
                case 4:
                    geriDefesa();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        } catch (MinhaExcecao me) {
            throw me; // Propaga a exceção personalizada
        } catch (Exception e) {
            throw new MinhaExcecao("Ocorreu um erro no menu principal", e);
        }
    }

    private static void gerirMenuRestaurante() throws MinhaExcecao {
        MenuRestaurante.menuItem();
    }

    private static void gerirMenuItem() throws MinhaExcecao {
        Prato.menuItem();
    }

    private static void gerirMenuData() throws MinhaExcecao {
        MenuDta.menuItem();
    }
    
    private static void geriDefesa() throws MinhaExcecao {
        Defesa.menuItem();
    }
}
