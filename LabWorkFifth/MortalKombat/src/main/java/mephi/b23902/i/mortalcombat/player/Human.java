
package mephi.b23902.i.mortalcombat.player;

import javax.swing.ImageIcon;

/**
 * Класс, представляющий игрока-человека в игре Mortal Kombat.
 * Наследуется от базового класса Player и добавляет специфичные для игрока характеристики:
 * - Очки для улучшений
 * - Опыт и систему уровней
 * - Счетчик побед
 */
public class Human extends Player {
    
    /** Количество очков для улучшений */
    private int points;
    
    /** Текущее количество опыта */
    private int experience;
    
    /** Количество побед */
    private int win;
    
    /** Опыт, необходимый для следующего уровня */
    private int nextexperience;
    
    /**
     * Конструктор для создания экземпляра игрока-человека.
     * 
     * @param level начальный уровень
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param attack сила атаки
     */
    public Human(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        this.points = 0;
        this.experience = 0;
        this.nextexperience = 40;
        this.win = 0;
        picture = new ImageIcon(this.getClass().getResource("/Player.png"));
    }
    
    /**
     * Возвращает количество очков улучшения.
     * 
     * @return текущее количество очков
     */
    public int getPoints() {
        return this.points;
    }
    
    /**
     * Возвращает текущее количество опыта.
     * 
     * @return текущий опыт
     */
    public int getExperience() {
        return this.experience;
    }
    
    /**
     * Возвращает количество опыта для следующего уровня.
     * 
     * @return необходимый опыт для уровня
     */
    public int getNextExperience() {
        return this.nextexperience;
    }
    
    /**
     * Возвращает количество побед.
     * 
     * @return текущее количество побед
     */
    public int getWin() {
        return this.win;
    }

    /**
     * Добавляет очки улучшения.
     * 
     * @param p количество добавляемых очков
     */
    public void setPoints(int p) {
        this.points += p;
    }
    
    /**
     * Добавляет опыт.
     * 
     * @param e количество добавляемого опыта
     */
    public void setExperience(int e) {
        this.experience += e;
    }
    
    /**
     * Устанавливает количество опыта для следующего уровня.
     * 
     * @param e новое значение необходимого опыта
     */
    public void setNextExperience(int e) {
        this.nextexperience = e;
    }
    
    /**
     * Увеличивает счетчик побед на 1.
     */
    public void setWin() {
        this.win++;
    }
    
    /**
     * Возвращает имя игрока.
     * 
     * @return строку "You" (представление игрока в интерфейсе)
     */
    @Override
    public String getName() {
        return "You";
    }
}