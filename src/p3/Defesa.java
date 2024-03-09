package p3;

import isptec.listas.Listas;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Defesa
{

    public static void menuItem() throws MinhaExcecao
    {
        String[] opcoes =
        {
            "Mostrar Menus", "Outra Opção", "Voltar"
        };
        int opcao;

        do
        {
            opcao = Listas.enviarLerOpcaoEscolhida(opcoes);

            switch (opcao)
            {
                case 1:
                    mostrarMenus();
                    break;
                case 2:
                  
                    break;
                case 3:
                    MenuPrincipal.menuPrincipal();
                    break;
            }
        }
        while (opcao != 0);
    }

    // Método para mostrar os menus
   private static void mostrarMenus() {
    try {
        List<MenuRestaurante> menus = MenuRestaurante.obterMenus();

        if (menus.isEmpty()) {
            System.out.println("Nenhum menu disponível.");
        } else {
            // Ordena os menus por nome (ordem alfabética)
            Collections.sort(menus, (menu1, menu2) -> menu1.getNome().compareToIgnoreCase(menu2.getNome()));

            System.out.println("Menus Disponíveis (Ordem Alfabética):");
            for (MenuRestaurante menu : menus) {
                System.out.println(menu.toString());
                System.out.println("-----------------------------------");
            }
        }
    } catch (MinhaExcecao me) {
        System.err.println("Ocorreu uma MinhaExcecao: " + me.getMessage());
        me.printStackTrace();
    }
}

    public static List<MenuRestaurante> inicializarMenusOrdenados()
    {
        try
        {
       
            List<MenuRestaurante> menus = MenuRestaurante.obterMenus();


            Collections.sort(menus, Comparator.comparing(MenuRestaurante::getNome));

            return menus;
        }
        catch (Exception e)
        {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
