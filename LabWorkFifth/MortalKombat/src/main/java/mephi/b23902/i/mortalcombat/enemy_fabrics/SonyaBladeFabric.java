
package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.enemys.SonyaBlade;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Фабрика для создания противника типа Sonya Blade.
 * Реализует паттерн Factory Method через интерфейс EnemyFabricInterface,
 * создавая экземпляр Sonya Blade с фиксированными базовыми характеристиками.
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    /**
     * Создает нового противника Sonya Blade с предопределенными характеристиками:
     * <ul>
     *   <li><strong>Уровень:</strong> 1</li>
     *   <li><strong>Здоровье:</strong> 80</li>
     *   <li><strong>Сила атаки:</strong> 16</li>
     *   <li><strong>Навык:</strong> 1</li>
     * </ul>
     *
     * @param i параметр уровня сложности (в текущей реализации не используется,
     *          но сохраняется для совместимости с интерфейсом)
     * @return новый экземпляр класса SonyaBlade с базовыми характеристиками
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }
}