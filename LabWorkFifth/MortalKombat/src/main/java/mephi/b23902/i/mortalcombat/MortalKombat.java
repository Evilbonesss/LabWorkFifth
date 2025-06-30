
package mephi.b23902.i.mortalcombat;

/**
 * Главный класс приложения Mortal Kombat, содержащий точку входа в программу.
 * Инициализирует и запускает главное окно приложения.
 */
public class MortalKombat {

    /**
     * Главный метод, являющийся точкой входа в приложение.
     * Создает и отображает главное окно приложения.
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        JFrames app = new JFrames();
        app.setVisible(true);
    }
}