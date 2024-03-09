package p3;
    
import isptec.listas.Listas;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuDta {

    private List<MenuPorDia> menusPorDia;

    public MenuDta() {
        this.menusPorDia = new ArrayList<>();
    }
    
    public static void menuItem() {
        MenuDta menuDta = new MenuDta(); // Crie uma instância de MenuDta

        String[] opcoes = {
                "Adicionar Menu Por Dia", "Imprimir Menus Por Dia", "Sair"
        };
        int opcao;
        do {
            opcao = Listas.enviarLerOpcaoEscolhida(opcoes);
            switch (opcao) {
                case 1:
                    menuDta.adicionarMenuPorDia();
                    break;
                case 2:
                    menuDta.imprimirMenusPorDia();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 3);
    }

    public void adicionarMenuPorDia(Date data, MenuRestaurante menu) {
        MenuPorDia menuPorDia = new MenuPorDia(data, menu);
        menusPorDia.add(menuPorDia);
    }

    public MenuRestaurante obterMenuPorDia(Date data) {
        for (MenuPorDia menuPorDia : menusPorDia) {
            if (menuPorDia.getData().equals(data)) {
                return menuPorDia.getMenu();
            }
        }
        return null;
    }

    public void imprimirMenusPorDia() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (MenuPorDia menuPorDia : menusPorDia) {
            Date data = menuPorDia.getData();
            MenuRestaurante menu = menuPorDia.getMenu();

            System.out.println("Data: " + sdf.format(data));
            System.out.println("Menu: " + menu.getNome());

            // Imprime os detalhes dos pratos associados ao menu
            if (menu.getPratos() != null && !menu.getPratos().isEmpty()) {
                System.out.println("Pratos:");
                for (Prato prato : menu.getPratos()) {
                    System.out.println("  - Nome do Prato: " + prato.getNome());
                    System.out.println("    Descrição do Prato: " + prato.getDescricao());
                    System.out.println("    Preço do Prato: " + prato.getPreco());
                    System.out.println("    Tipo do Prato: " + prato.getTipoPrato());
                }
            } else {
                System.out.println("Este menu não possui pratos.");
            }

            System.out.println("-----------------------------------");
        }
    }

    // Função para adicionar um menu existente do arquivo MenuRestaurante
    public void adicionarMenuPorDia() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite a data (formato dd/MM/yyyy): ");
    String dataString = scanner.nextLine();

    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf.parse(dataString);

        MenuRestaurante menuExistente = obterMenuExistente(); // Obtém um menu existente do arquivo MenuRestaurante
        if (menuExistente != null) {
            MenuPorDia menuPorDia = new MenuPorDia(data, menuExistente);
            menusPorDia.add(menuPorDia);
            System.out.println("Menu adicionado com sucesso para a data: " + data);
        } else {
            System.out.println("Menu não encontrado no arquivo MenuRestaurante.");
        }
    } catch (Exception e) {
        System.out.println("Erro ao processar a data: " + e.getMessage());
    }
}

// Método privado para obter um menu existente do arquivo MenuRestaurante
private MenuRestaurante obterMenuExistente() {
    // Implemente a lógica para obter um menu existente do arquivo MenuRestaurante

    // Lê os menus do arquivo MenuRestaurante
    List<MenuRestaurante> menus = lerMenusDoArquivo();

    if (!menus.isEmpty()) {
        // Exibe os menus disponíveis
        System.out.println("Menus disponíveis:");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getNome());
        }

        // Solicita ao usuário que escolha um menu
        System.out.print("Escolha o número do menu desejado: ");
        int escolha = P3.scanner.nextInt();

        // Verifica se a escolha é válida
        if (escolha >= 1 && escolha <= menus.size()) {
            // Retorna o menu escolhido
            return menus.get(escolha - 1);
        } else {
            System.out.println("Escolha inválida.");
        }
    } else {
        System.out.println("Nenhum menu disponível.");
    }

    // Retorna null se nenhum menu for escolhido
    return null;
}

private List<MenuRestaurante> lerMenusDoArquivo() {
    List<MenuRestaurante> menus = new ArrayList<>();

    try (RandomAccessFile file = new RandomAccessFile("Ficheiros/Menu.dat", "r")) {
        // Lê os menus do arquivo
        while (file.getFilePointer() < file.length()) {
            String nomeMenu = file.readUTF();
            // Adicione aqui a lógica para ler outros atributos do menu, se necessário
            MenuRestaurante menu = new MenuRestaurante(nomeMenu);
            // Configure outros atributos do menu, se necessário
            menus.add(menu);
        }
    } catch (IOException e) {
        System.err.println("Erro ao ler os menus: " + e.getMessage());
    }

    return menus;
}


    // Classe interna para representar um menu associado a uma data
    private static class MenuPorDia {
        private Date data;
        private MenuRestaurante menu;

        public MenuPorDia(Date data, MenuRestaurante menu) {
            this.data = data;
            this.menu = menu;
        }

        public Date getData() {
            return data;
        }

        public MenuRestaurante getMenu() {
            return menu;
        }
    }
}