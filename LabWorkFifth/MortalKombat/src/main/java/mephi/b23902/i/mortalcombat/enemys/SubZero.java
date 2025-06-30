package mephi.b23902.i.mortalcombat.enemys;

import mephi.b23902.i.mortalcombat.player.Player;
import javax.swing.ImageIcon;

/**
 * Класс, представляющий персонажа Sub-Zero в игре Mortal Kombat.
 * Наследуется от базового класса Player.
 * Sub-Zero обладает магическими способностями (криомантия).
 */
public class SubZero extends Player {
    
    /**
     * Конструктор для создания экземпляра Sub-Zero.
     * 
     * @param level уровень персонажа
     * @param health количество здоровья
     * @param damage наносимый урон
     * @param attack сила атаки
     */
    public SubZero(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        picture = new ImageIcon(this.getClass().getResource("/SubZero.png"));
    }
    
    /**
     * Возвращает имя персонажа.
     * 
     * @return строку с именем персонажа "Sub-Zero"
     */
    @Override
    public String getName() {
        return "Sub-Zero";
    }
    
    /**
     * Проверяет, является ли персонаж магом.
     * Sub-Zero владеет криомагией, поэтому возвращает true.
     * 
     * @return true - персонаж является магом, false - не является
     */
    @Override
    public Boolean isWizard() {
        return true;
    }
}