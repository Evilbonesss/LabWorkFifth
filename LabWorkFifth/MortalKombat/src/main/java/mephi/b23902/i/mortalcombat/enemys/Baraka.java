/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b23902.i.mortalcombat.enemys;

import mephi.b23902.i.mortalcombat.player.Player;
import javax.swing.ImageIcon;

/**
 * Класс персонажа Baraka, представляющий конкретного противника в Mortal Kombat.
 * Расширяет базовый класс Player, добавляя специфичные для Baraka свойства и поведение.
 * 
 * Baraka характеризуется уникальным внешним видом и стилем боя.
 * Этот класс управляет его визуальным представлением и именем.
 */
public class Baraka extends Player {
    
    /**
     * Создает новый экземпляр Baraka с указанными боевыми характеристиками.
     * 
     * @param level уровень персонажа (влияет на общую мощь)
     * @param health начальное количество очков здоровья
     * @param damage базовый урон
     * @param attack уровень навыка атаки
     */
    public Baraka(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        picture = new ImageIcon(this.getClass().getResource("/Baraka.png"));
    }
    
    /**
     * Возвращает имя персонажа.
     * 
     * @return строка "Baraka", идентифицирующая этого персонажа
     */
    @Override
    public String getName() {
        return "Baraka";
    }
}