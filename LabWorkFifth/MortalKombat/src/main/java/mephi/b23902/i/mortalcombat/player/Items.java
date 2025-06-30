
package mephi.b23902.i.mortalcombat.player;

/**
 * Класс, представляющий игровой предмет.
 * Хранит информацию о названии предмета и его количестве в инвентаре.
 */
public class Items {
    
    /** Название предмета */
    private String name;
    
    /** Количество предметов в инвентаре */
    private int count;
    
    /**
     * Создает новый предмет с указанными параметрами.
     * 
     * @param n название предмета
     * @param c начальное количество предметов
     */
    public Items(String n, int c) {
        this.name = n;
        this.count = c;
    }
    
    /**
     * Устанавливает новое название предмета.
     * 
     * @param s новое название предмета
     */
    public void setName(String s) {
        this.name = s;
    }
    
    /**
     * Изменяет количество предметов на указанное значение.
     * Для уменьшения количества передавайте отрицательное число.
     * 
     * @param c количество для добавления/удаления
     */
    public void setCount(int c) {
        this.count += c;
    }
    
    /**
     * Возвращает название предмета.
     * 
     * @return название предмета
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Возвращает текущее количество предметов.
     * 
     * @return количество предметов в инвентаре
     */
    public int getCount() {
        return this.count;
    }
}