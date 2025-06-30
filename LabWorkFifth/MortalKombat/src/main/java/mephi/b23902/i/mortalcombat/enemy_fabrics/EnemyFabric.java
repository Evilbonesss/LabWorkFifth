
package mephi.b23902.i.mortalcombat.enemy_fabrics;

import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Фабрика для создания различных типов противников.
 * Позволяет создавать конкретные экземпляры врагов на основе переданных параметров.
 */
public class EnemyFabric {

    /**
     * Создает противника указанного типа и уровня.
     * 
     * @param i тип создаваемого противника:
     *          0 - Baraka,
     *          1 - SubZero,
     *          2 - LiuKang,
     *          3 - SonyaBlade,
     *          4 - ShaoKahn
     * @param j уровень сложности/характеристик противника
     * @return новый экземпляр противника заданного типа
     * @throws IllegalArgumentException если передан недопустимый тип противника
     */
    public Player create(int i, int j) {
        EnemyFabricInterface fabric = null;

        switch (i) {
            case 0:
                fabric = new BarakaFabric();
                break;
            case 1:
                fabric = new SubZeroFabric();
                break;
            case 2:
                fabric = new LiuKangFabric();
                break;
            case 3:
                fabric = new SonyaBladeFabric();
                break;
            case 4:
                fabric = new ShaoKahnFabric();
                break;
            default:
                throw new IllegalArgumentException("Invalid enemy type: " + i);
        }
        Player enemy = fabric.create(j);
        return enemy;
    }
}