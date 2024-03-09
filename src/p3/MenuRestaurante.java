package p3;

import isptec.Defs;
import isptec.listas.Listas;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MenuRestaurante
{

    private static final String MENU_FILE = Defs.MENU_MENU_FILE;
    private String nome;
    private List<Prato> pratos;
    private static RandomAccessFile file = null;

    public MenuRestaurante(String nome)
    {
        this.nome = nome;
        this.pratos = new ArrayList<>();
    }

    public static void menuItem() throws MinhaExcecao
    {
        String[] opcoes =
        {
            "Novo", "Remover", "Imprimir", "Consultas", "Editar", "Adicionar Prato", "Voltar"
        };
        int opcao;
        do
        {
            opcao = Listas.enviarLerOpcaoEscolhida(opcoes);
            switch (opcao)
            {
                case 1:
                    criarMenu();
                    break;
                case 2:
                    eliminarMenu();
                    break;
                case 3:
                    imprimirMenus();
                    break;
                case 4:
                    pesquisarMenu();
                    break;
                case 5:
                    editarMenu();
                    break;
                case 6:
                    adicionarPrato();
                    break;
                case 7:
                    MenuPrincipal.menuPrincipal();
                    break;
            }
        }
        while (opcao != 0);
    }

    public static void criarMenu()
    {
        System.out.println("Criando um novo menu...");

        System.out.print("Nome do Menu: ");
        String nomeMenu = P3.scanner.nextLine();

        if (!menuExiste(nomeMenu))
        {
            MenuRestaurante novoMenu = new MenuRestaurante(nomeMenu);
            novoMenu.setNome(nomeMenu);
            gravarMenuNoArquivo(novoMenu);
            System.out.println("Menu \"" + novoMenu.getNome() + "\" adicionado com sucesso!");
        }
        else
        {
            System.out.println("Menu com o nome \"" + nomeMenu + "\" já existe.");
        }
    }

    private static boolean menuExiste(String nomeMenu)
    {
        abrirFicheiro();
        try
        {
            while (file.getFilePointer() < file.length())
            {
                String nomeExistente = file.readUTF();
                if (nomeExistente.equals(nomeMenu))
                {
                    return true;
                }
                // Pule os outros atributos do menu, se houver
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao verificar a existência do menu: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro();
        }
        return false;
    }

    private static void gravarMenuNoArquivo(MenuRestaurante menu)
    {
        abrirFicheiro();
        try
        {
            file.seek(file.length()); // Posiciona no final do arquivo
            file.writeUTF(menu.getNome());
            // Adicione aqui a lógica para gravar outros atributos do menu, se necessário
        }
        catch (IOException e)
        {
            System.err.println("Erro ao gravar o menu: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro();
        }
    }

// ...
    public static void imprimirMenus()
    {
        abrirFicheiro(); // Abre o arquivo para leitura
        List<MenuRestaurante> menus = new ArrayList<>();

        try
        {
            file.seek(0); // Posiciona no início do arquivo
            while (file.getFilePointer() < file.length())
            {
                String nomeMenu = file.readUTF();


                MenuRestaurante menu = new MenuRestaurante(nomeMenu);
                menu.setNome(nomeMenu);
                menus.add(menu);
            }

          

            for (MenuRestaurante menu : menus)
            {
                System.out.println(menu.toString());
                System.out.println("-----------------------------------");
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao ler os menus: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro(); 
        }
    }

// ...
    public static void eliminarMenu()
    {
        System.out.print("Digite o nome do menu a ser eliminado: ");
        String nomeMenu = P3.scanner.nextLine();

        if (!menuExiste(nomeMenu))
        {
            System.out.println("Menu não encontrado.");
            return;
        }

        File tempFile = new File(Defs.tmpFile);
        boolean menuEncontrado = false;

        abrirFicheiro(); 

        try
        {
            RandomAccessFile tempRAF = new RandomAccessFile(tempFile, "rw"); 

            // Percorre o arquivo original
            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();

                if (!nome.equals(nomeMenu))
                {
                  
                    tempRAF.writeUTF(nome);
                   
                }
                else
                {
                    menuEncontrado = true; // 
                }
            }

            
            file.close();
            tempRAF.close();

           
            if (menuEncontrado)
            {
                File originalFile = new File(Defs.MENU_MENU_FILE);
                if (originalFile.delete())
                {
                    tempFile.renameTo(originalFile);
                    System.out.println("Menu removido com sucesso.");
                }
                else
                {
                    System.err.println("Erro ao remover o menu.");
                }
            }
            else
            {
                System.out.println("Menu não encontrado.");
                tempFile.delete(); 
            }

        }
        catch (IOException e)
        {
            System.err.println("Erro ao eliminar o menu: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro();
        }
    }

    public static void pesquisarMenu()
    {
        abrirFicheiro();
        System.out.print("Digite o nome do menu que deseja pesquisar: ");
        String nomeMenu = P3.scanner.nextLine();

        try
        {
            boolean menuEncontrado = false;

            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();
         

                if (nome.equals(nomeMenu))
                {
                    menuEncontrado = true;

         
                    MenuRestaurante menu = new MenuRestaurante(nome);
                    menu.setNome(nome);
               
                    System.out.println("Menu encontrado:");
                    System.out.println(menu.toString());
                    break; // 
                }
            }

            if (!menuEncontrado)
            {
                System.out.println("Menu não encontrado.");
            }

            file.close();
        }
        catch (IOException e)
        {
            System.err.println("Erro ao pesquisar o menu: " + e.getMessage());
        }
    }

    public static void editarMenu()
    {
        try (RandomAccessFile file = new RandomAccessFile(Defs.MENU_MENU_FILE, "rw"))
        {
            abrirFicheiro();
            System.out.print("Digite o nome do menu que deseja editar: ");
            String nomeMenu = P3.scanner.nextLine();

            List<MenuRestaurante> menus = new ArrayList<>();
            boolean menuEncontrado = false;


            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();


                MenuRestaurante menu = new MenuRestaurante(nome);
                menu.setNome(nome);
                // Configure outros atributos do menu, se necessário
                menus.add(menu);

                if (nome.equals(nomeMenu))
                {
                    menuEncontrado = true;

                    // Solicita e aplica as alterações
                    System.out.print("Novo nome do Menu: ");
                    String novoNome = P3.scanner.nextLine();
                    if (menuExiste(novoNome))
                    {
                        System.out.println("Já existe um menu com esse nome.");
                        return;
                    }

                    // Atualiza o menu na memória
                    menu.setNome(novoNome);
                    // Configure outros atributos do menu, se necessário
                }
            }

            // Se encontrou o menu, reescreve o arquivo com os menus atualizados
            if (menuEncontrado)
            {
                file.setLength(0); // Limpa o conteúdo do arquivo antes de reescrever
                for (MenuRestaurante menu : menus)
                {
                    file.writeUTF(menu.getNome());
                    // Adicione aqui a lógica para gravar outros atributos do menu, se necessário
                }
                System.out.println("Menu editado com sucesso.");
            }
            else
            {
                System.out.println("Menu não encontrado.");
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao editar o menu: " + e.getMessage());
        }
    }

    //Adicionar Prato
    public static void adicionarPrato()
    {

        imprimirMenus(); // Imprime os menus disponíveis para escolha
        System.out.print("Digite o nome do menu ao qual deseja adicionar o prato: ");
        String nomeMenu = P3.scanner.nextLine();

        MenuRestaurante menu = obterMenuPorNome(nomeMenu);
        if (menu != null)
        {
            Prato.imprimirPratos();
            System.out.println("Digite o nome do prato a adicionar no Menu");
            String nomePrato = P3.scanner.nextLine();
            Prato pratoExistente = Prato.buscarPratoPorNome(nomePrato); // Obtém um prato existente do arquivo Prato
            if (pratoExistente != null)
            {
                if (menu.getPratos() == null)
                {
                    menu.setPratos(new ArrayList<>());
                }

                menu.getPratos().add(pratoExistente);
                System.out.println("Prato adicionado com sucesso ao menu: " + nomeMenu);
            }
            else
            {
                System.out.println("Prato não encontrado no arquivo Prato.");
            }
        }
        else
        {
            System.out.println("Menu não encontrado.");
        }
    }

    //Outras funcoes
    private static void abrirFicheiro()
    {
        if (file == null)
        {
            try
            {
                file = new RandomAccessFile(MENU_FILE, "rw");
            }
            catch (IOException e)
            {
                System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            }
        }
    }

    private static void fecharFicheiro()
    {
        if (file != null)
        {
            try
            {
                file.close();
                file = null;
            }
            catch (IOException e)
            {
                System.err.println("Erro ao fechar o arquivo: " + e.getMessage());
            }
        }
    }

    public static MenuRestaurante obterMenuPorNome(String nomeMenu)
    {
        abrirFicheiro();

        try
        {
            while (file.getFilePointer() < file.length())
            {
                String nome = file.readUTF();

                // Adicione aqui a lógica para ler outros atributos do menu, se necessário
                if (nome.equals(nomeMenu))
                {
                    // Exibir as informações do menu encontrado usando toString
                    MenuRestaurante menu = new MenuRestaurante(nome);
                    menu.setNome(nome);
                    // Adicione aqui a lógica para definir outros atributos do menu, se necessário
                    return menu;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Erro ao obter o menu: " + e.getMessage());
        }
        finally
        {
            fecharFicheiro();
        }

        return null; // Retorna null se o menu não for encontrado
    }

    

    public static List<MenuRestaurante> obterMenus() throws MinhaExcecao
    {
        List<MenuRestaurante> menus = new ArrayList<>();
        RandomAccessFile file = null;

        try
        {
            file = new RandomAccessFile(Defs.MENU_MENU_FILE, "rw");

            while (file.getFilePointer() < file.length())
            {
                String nomeMenu = file.readUTF();

                MenuRestaurante menu = new MenuRestaurante(nomeMenu);
                menu.setNome(nomeMenu);
                menus.add(menu);
            }
        }
        catch (IOException e)
        {
            throw new MinhaExcecao("Erro ao obter os menus: " + e.getMessage(), e);
        }
        finally
        {
            if (file != null)
            {
                try
                {
                    file.close();
                }
                catch (IOException e)
                {
                    System.err.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }

        return menus;
    }

    // Getters e Setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public List<Prato> getPratos()
    {
        return pratos;
    }

    public void setPratos(List<Prato> pratos)
    {
        this.pratos = pratos;
    }

    public void imprimirPratos()
    {
        if (this.pratos != null)
        {
            Prato.imprimirPratos(this.pratos);
        }
        else
        {
            System.out.println("Nenhum prato disponível neste menu.");
        }
    }

    @Override
    public String toString()
    {
        return "MenuRestaurante{" + "nome=" + nome + ", pratos=" + pratos + '}';
    }

}
