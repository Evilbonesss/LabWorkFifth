package mephi.b23902.i.mortalcombat.enemys;

import mephi.b23902.i.mortalcombat.player.Player;
import javax.swing.ImageIcon;

/**
 * Класс, представляющий персонажа Sonya Blade в игре Mortal Kombat.
 * Наследуется от базового класса Player.
 */
public class SonyaBlade extends Player {
    
    /**
     * Конструктор для создания экземпляра Sonya Blade.
     * 
     * @param level уровень персонажа
     * @param health количество здоровья
     * @param damage наносимый урон
     * @param attack сила атаки
     */
    public SonyaBlade(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        picture = new ImageIcon(this.getClass().getResource("/SonyaBlade.png"));
    }
    
    /**
     * Возвращает имя персонажа.
     * 
     * @return строку с именем персонажа "Sonya Blade"
     */
    @Override
    public String getName() {
        return "Sonya Blade";
    }
}