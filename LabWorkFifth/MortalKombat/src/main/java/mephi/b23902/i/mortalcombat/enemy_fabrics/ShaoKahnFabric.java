package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.enemys.ShaoKahn;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Фабрика для создания противника типа Shao Kahn с различными уровнями сложности.
 * Реализует интерфейс EnemyFabricInterface, предоставляя два варианта характеристик
 * для этого босса в зависимости от переданного параметра.
 */
public class ShaoKahnFabric implements EnemyFabricInterface {
    
    /**
     * Создает экземпляр Shao Kahn с характеристиками, зависящими от уровня сложности.
     * 
     * @param i уровень сложности:
     *          0 - стандартные характеристики (уровень 3, здоровье 100, сила 30)
     *          другие значения - усиленные характеристики (уровень 3, здоровье 145, сила 44)
     * @return новый экземпляр противника Shao Kahn с заданными характеристиками
     */
    @Override
    public Player create(int i) {
        Player enemy;
        if(i == 0) {
            enemy = new ShaoKahn(3, 100, 30, 1);  // Стандартная версия
        }
        else {
           enemy = new ShaoKahn(3, 145, 44, 1);   // Усиленная версия 
        }
        return enemy;
    }
}