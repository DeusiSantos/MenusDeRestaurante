package p3;

import java.util.Date;

public class MenuPorDia implements Comparable<MenuPorDia> {
    private MenuRestaurante menu;
    private Date data;

    public MenuPorDia(MenuRestaurante menu, Date data) {
        this.menu = menu;
        this.data = data;
    }

    public MenuRestaurante getMenu() {
        return menu;
    }

    public Date getData() {
        return data;
    }

    @Override
    public int compareTo(MenuPorDia outroMenuPorDia) {
        return this.menu.getNome().compareTo(outroMenuPorDia.menu.getNome());
    }
}
