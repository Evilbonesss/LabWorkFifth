package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.enemys.Baraka;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Фабрика для создания противника типа Baraka.
 * Реализует интерфейс EnemyFabricInterface для создания конкретного типа врага.
 */
public class BarakaFabric implements EnemyFabricInterface {

    /**
     * Создает и возвращает нового противника типа Baraka с заданными характеристиками.
     * 
     * @param i уровень сложности противника (в текущей реализации не используется)
     * @return новый объект противника типа Baraka
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}
