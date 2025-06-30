/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.enemys.SubZero;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Фабричный класс для создания экземпляров противника SubZero.
 * Реализует интерфейс EnemyFabricInterface, предоставляя стандартные
 * характеристики для этого типа персонажа.
 * 
 * <p>Создаваемый SubZero имеет следующие параметры:
 * <ul>
 *   <li><b>Уровень:</b> 1</li>
 *   <li><b>Здоровье:</b> 60</li>
 *   <li><b>Сила атаки:</b> 16</li>
 *   <li><b>Навык:</b> 1</li>
 * </ul>
 */
public class SubZeroFabric implements EnemyFabricInterface {

    /**
     * Создает нового противника типа SubZero с фиксированными характеристиками.
     * 
     * @param i параметр уровня сложности (не используется в текущей реализации,
     *          но требуется интерфейсом EnemyFabricInterface)
     * @return новый экземпляр класса SubZero с базовыми характеристиками
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SubZero(1, 60, 16, 1);
        return enemy;
    }
}