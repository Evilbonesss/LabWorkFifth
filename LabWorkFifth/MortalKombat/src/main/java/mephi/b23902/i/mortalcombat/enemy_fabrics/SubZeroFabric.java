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
 * Создаваемый SubZero имеет следующие параметры:
 * 
 * - Уровень: 1
 * - Здоровье: 60
 * - Сила атаки: 16
 * - Навык: 1
 *
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