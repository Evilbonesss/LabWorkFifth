package mephi.b23902.i.mortalcombat.player;

import javax.swing.ImageIcon;

/**
 * Класс, представляющий игрока в Mortal Combat.
 * Содержит основные характеристики игрока и методы для управления ими.
 */
public class Player {
    
    private int level;
    private int health;
    private int maxhealth;
    private double damage;
    private int attack;
    public ImageIcon picture;
    private boolean weakness = false;
    private int timer = 0;
    
    /**
     * Конструктор для создания нового игрока.
     * 
     * @param level начальный уровень игрока
     * @param health начальное здоровье игрока
     * @param damage начальный урон игрока
     * @param attack начальная атака игрока
     */
    public Player(int level, int health, double damage, int attack){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxhealth=health;
    }
   
    /**
     * Увеличивает уровень игрока на 1.
     */
    public void setLevel(){
        this.level++;
    }
    
    /**
     * Изменяет здоровье игрока.
     * Если игрок ослаблен (weakness), получает на 25% больше урона.
     * 
     * @param h количество здоровья для добавления (может быть отрицательным для урона)
     */
    public void setHealth(double h){
        if (h < 0 && weakness){
            this.health += h * 1.25;
        }
        else {
            this.health+=h;
        }
    }
    
    /**
     * Устанавливает новое значение здоровья игрока.
     * 
     * @param h новое значение здоровья
     */
    public void setNewHealth(int h){
        this.health=h;
    }
    
    /**
     * Изменяет урон игрока.
     * 
     * @param d значение для добавления к текущему урону
     */
    public void setDamage(double d){
        this.damage+=d;
    }
    
    /**
     * Устанавливает значение атаки игрока.
     * 
     * @param a новое значение атаки
     */
    public void setAttack(int a){
        this.attack=a;
    }
    
    /**
     * Изменяет максимальное здоровье игрока.
     * 
     * @param h значение для добавления к текущему максимальному здоровью
     */
    public void setMaxHealth(int h){
        this.maxhealth+=h;
    }
    
    /**
     * Возвращает текущий уровень игрока.
     * 
     * @return текущий уровень
     */
    public int getLevel(){
        return this.level;
    }
    
    /**
     * Возвращает текущее здоровье игрока.
     * 
     * @return текущее здоровье
     */
    public int getHealth(){
        return this.health;
    }
    
    /**
     * Возвращает текущий урон игрока.
     * Если игрок ослаблен (weakness), урон уменьшается вдвое.
     * 
     * @return текущий урон (с учетом модификаторов)
     */
    public double getDamage(){
        if (weakness) {
            return this.damage * 0.5;
        }
        return this.damage;
    }
    
    /**
     * Возвращает текущую атаку игрока.
     * 
     * @return значение атаки
     */
    public int getAttack(){
        return this.attack;
    }
    
    /**
     * Возвращает максимальное здоровье игрока.
     * 
     * @return максимальное здоровье
     */
    public int getMaxHealth(){
        return this.maxhealth;
    }
    
    /**
     * Возвращает имя игрока.
     * Базовый класс возвращает пустую строку, должен быть переопределен в подклассах.
     * 
     * @return имя игрока
     */
    public String getName(){
        return "";
    }
    
    /**
     * Возвращает изображение игрока.
     * 
     * @return ImageIcon с изображением игрока
     */
    public ImageIcon getPicture() {
        return picture;
    }
     
    /**
     * Устанавливает состояние слабости игрока на определенное количество ходов.
     * 
     * @param level количество ходов, на которое устанавливается слабость
     */
    public void setWeakness(int level) {
         timer = level;
         weakness = true;
     }
     
    /**
     * Уменьшает таймер слабости и снимает слабость, если таймер достиг нуля.
     */
    public void removeWeakness() {
         if (timer > 0) timer--;
         else {
             weakness = false;
         }
     }
     
    /**
     * Проверяет, является ли игрок магом.
     * Базовый класс возвращает false, должен быть переопределен в подклассах.
     * 
     * @return true если игрок волшебник, false в противном случае
     */
    public Boolean isWizard() {
         return false;
     }
}