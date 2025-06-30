package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Интерфейс фабрики для создания противников.
 * Определяет контракт для классов, создающих конкретные типы врагов.
 */
public interface EnemyFabricInterface {
    
    /**
     * Создает нового противника с заданным уровнем сложности.
     * 
     * @param i уровень сложности/характеристик создаваемого противника
     * @return новый экземпляр противника (Player)
     */
    public Player create(int i);
}