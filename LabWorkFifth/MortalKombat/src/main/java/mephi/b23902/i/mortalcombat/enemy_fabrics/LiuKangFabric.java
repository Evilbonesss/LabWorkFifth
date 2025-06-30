package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.enemys.LiuKang;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Конкретная фабрика для создания противника типа Liu Kang.
 * Реализует интерфейс EnemyFabricInterface, предоставляя конкретную реализацию
 * создания персонажа Liu Kang с заданными характеристиками.
 */
public class LiuKangFabric implements EnemyFabricInterface {

    /**
     * Создает и возвращает нового противника типа Liu Kang.
     * Характеристики противника фиксированы:
     * 
     * - Уровень: 1
     * - Здоровье: 70
     * - Сила: 20
     * - Навык: 1
     * 
     * @param i параметр уровня (в текущей реализации не используется,
     *          но требуется интерфейсом для возможного расширения функциональности)
     * @return новый экземпляр противника типа Liu Kang
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new LiuKang(1, 70, 20, 1);
        return enemy;
    }
}