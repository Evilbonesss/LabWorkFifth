package mephi.b23902.i.mortalcombat.enemys;

import mephi.b23902.i.mortalcombat.player.Player;
import javax.swing.ImageIcon;

/**
 * Класс, представляющий персонажа Shao Kahn в игре Mortal Combat.
 * Наследуется от базового класса Player.
 */
public class ShaoKahn extends Player {
    
    /**
     * Конструктор для создания экземпляра Shao Kahn.
     * 
     * @param level уровень персонажа
     * @param health количество здоровья
     * @param damage наносимый урон
     * @param attack сила атаки
     */
    public ShaoKahn(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        picture = new ImageIcon(this.getClass().getResource("/ShaoKahn.png"));
    }
    
    /**
     * Возвращает имя персонажа.
     * 
     * @return строку с именем персонажа "Shao Kahn"
     */
    @Override
    public String getName() {
        return "Shao Kahn";
    }
}