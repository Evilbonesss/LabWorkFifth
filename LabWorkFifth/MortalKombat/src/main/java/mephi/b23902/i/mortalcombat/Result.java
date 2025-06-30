/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b23902.i.mortalcombat;

/**
 * Класс для хранения и управления результатами игроков.
 * Содержит информацию об имени игрока и набранных им очках.
 */
public class Result {
    
    private String name;
    private int points;
    
    /**
     * Конструктор для создания нового результата.
     * 
     * @param n имя игрока
     * @param p количество набранных очков
     */
    public Result(String n, int p) {
        this.name = n;
        this.points = p;
    }
    
    /**
     * Устанавливает имя игрока.
     * 
     * @param s новое имя игрока
     */
    public void setName(String s) {
        this.name = s;
    }
    
    /**
     * Устанавливает количество очков игрока.
     * 
     * @param p новое количество очков
     */
    public void setPoints(int p) {
        this.points = p;
    }
    
    /**
     * Возвращает имя игрока.
     * 
     * @return текущее имя игрока
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Возвращает количество очков игрока.
     * 
     * @return текущее количество очков
     */
    public int getPoints() {
        return this.points;
    }
}