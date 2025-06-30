
package mephi.b23902.i.mortalcombat.enemys;

import mephi.b23902.i.mortalcombat.player.Player;
import javax.swing.ImageIcon;

/**
 * Класс персонажа Лю Кан, представляющий одного из главных героев Mortal Kombat.
 * Наследует базовый класс Player, добавляя специфичные характеристики персонажа.
 *
 * Лю Кан - мастер боевых искусств, известный своими мощными ударами ногами.
 * Класс управляет его визуальным представлением и основными характеристиками.
 */
public class LiuKang extends Player {
    
    /**
     * Создает нового персонажа Лю Кан с заданными параметрами.
     * 
     * @param level уровень персонажа (1-10)
     * @param health количество очков здоровья
     * @param damage базовый урон при атаке
     * @param attack уровень мастерства атаки
     */
    public LiuKang(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        picture = new ImageIcon(this.getClass().getResource("/LiuKang.png"));
    }
    
    /**
     * Возвращает каноническое имя персонажа.
     * 
     * @return строка "Liu Kang" - имя персонажа
     */
    @Override
    public String getName() {
        return "Liu Kang";
    }
}