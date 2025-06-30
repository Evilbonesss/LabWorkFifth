package mephi.b23902.i.mortalcombat.fight;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import mephi.b23902.i.mortalcombat.enemy_fabrics.EnemyFabric;
import mephi.b23902.i.mortalcombat.enemys.Baraka;
import mephi.b23902.i.mortalcombat.enemys.LiuKang;
import mephi.b23902.i.mortalcombat.enemys.ShaoKahn;
import mephi.b23902.i.mortalcombat.enemys.SonyaBlade;
import mephi.b23902.i.mortalcombat.enemys.SubZero;
import mephi.b23902.i.mortalcombat.player.Human;
import mephi.b23902.i.mortalcombat.player.Items;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Класс CharacterAction управляет действиями персонажей,
 * выбором и настройкой противников, начислением очков и опыта,
 * выдачей предметов, а также логикой поведения врагов.
 */
public class CharacterAction {

    /** Необходимое количество опыта для повышения уровня */
    private int experience_for_next_level = 40;
    
    /** Массив шаблонов поведения врага (атака/защита) */
    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};
    
    /** Массив доступных врагов */
    private Player enemyes[] = new Player[6];

    /** Фабрика для создания врагов */
    EnemyFabric fabric = new EnemyFabric();

    /** Текущий выбранный враг */
    private Player enemyy = null;

    /**
     * Создает массив врагов, используя EnemyFabric.
     */
    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
        enemyes[5] = fabric.create(4, 0);
    }

    /**
     * Возвращает массив всех врагов.
     * @return массив врагов
     */
    public Player[] getEnemyes() {
        return this.enemyes;
    }

    /**
     * Случайно выбирает врага для битвы, обновляет интерфейс.
     * @param label JLabel для изображения врага
     * @param label2 JLabel для имени врага
     * @param text JLabel для урона врага
     * @param label3 JLabel для HP врага
     * @return выбранный враг
     */
    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        int i = (int) (Math.random() * 4);
        ImageIcon icon1 = null;
        switch (i) {
            case 0:
                enemyy = enemyes[0];
                break;
            case 1:
                enemyy = enemyes[1];
                break;
            case 2:
                enemyy = enemyes[2];
                break;
            case 3:
                enemyy = enemyes[3];
                break;
        }
        label.setIcon(enemyy.getPicture());
        label2.setText(enemyy.getName());
        text.setText(String.valueOf(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    /**
     * Выбирает и настраивает босса для битвы, обновляет интерфейс.
     * @param label JLabel для изображения
     * @param label2 JLabel для имени
     * @param text JLabel для урона
     * @param label3 JLabel для HP
     * @param i текущий индекс врага
     * @param human игрок
     * @return босс
     */
    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i, Player human) {

        label2.setText("Shao Kahn - БОСС");
        enemyy = enemyes[4];
        for(int j=0; j<human.getLevel() - enemyy.getLevel() + 2; i++) {
            enemyy.setLevel();
        }
        enemyy.setDamage( enemyy.getLevel());
        enemyy.setMaxHealth(5 * enemyy.getLevel());
        enemyy.setHealth(enemyy.getMaxHealth() - enemyy.getHealth());
        System.out.println("health: " + enemyy.getHealth());
        label.setIcon(enemyy.getPicture());
        text.setText(String.valueOf(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }

    /**
     * Генерирует шаблон поведения врага на основе вероятностей.
     * @param k1 вероятность поведения 1
     * @param k2 вероятность поведения 2
     * @param k3 вероятность поведения 3
     * @param k4 вероятность поведения 4
     * @param i случайное число для выбора
     * @param isWizard признак мага
     * @return массив шаблона поведения
     */
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i, Boolean isWizard) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }

    /**
     * Выбирает шаблон поведения для конкретного врага.
     * @param enemy враг
     * @param action объект CharacterAction
     * @return массив действий поведения
     */
    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i, false);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i, true);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i, false);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i, false);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, 45, i, false);
        }
        return arr;
    }

    /**
     * Обновляет прогресс-бар здоровья игрока/врага.
     * @param player персонаж
     * @param progress JProgressBar для отображения здоровья
     */
    public void HP(Player player, JProgressBar progress) {
        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }
    
    /**
     * Заглушка для действия "Ослабление".
     */
    public void useWeakness() {
        
    }

    /**
     * Начисляет игроку опыт и очки за победу, обновляет уровень при необходимости.
     * @param human игрок
     * @param enemyes массив врагов
     * @return true, если уровень повышен
     */
    public Boolean AddPoints(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 0:
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
        }
        Boolean isLevelUp = false;
        System.out.println(experience_for_next_level + " и " +  human.getExperience());
        
        if (experience_for_next_level <= human.getExperience()) {   
            human.setLevel();
            isLevelUp = true;
            experience_for_next_level += human.getExperience();
            human.setNextExperience(experience_for_next_level);
            NewHealthHuman(human);
            for (int j = 0; j < 4; j++) {
                NewHealthEnemy(enemyes[j], human);
            }
        }
        return isLevelUp;
    }

    /**
     * Начисляет опыт и очки за победу над боссом, обновляет уровень при необходимости.
     * @param human игрок
     * @param enemyes массив врагов
     */
    public void AddPointsBoss(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 2:
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
                break;
        }
        if (experience_for_next_level <= human.getExperience()) {
            human.setLevel();
            experience_for_next_level += human.getExperience();
            human.setNextExperience(experience_for_next_level);
            NewHealthHuman(human);
            for (int j = 0; j < 4; j++) {
                NewHealthEnemy(enemyes[j], human);
            }
        }
    }

    /**
     * Генерирует выпадающий предмет для инвентаря игрока по заданным вероятностям.
     * @param k1 вероятность малого зелья
     * @param k2 вероятность большого зелья
     * @param k3 вероятность креста
     * @param items массив предметов
     */
    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    /**
     * Устанавливает новое значение здоровья и урона для игрока при повышении уровня.
     * @param human игрок
     */
    public void NewHealthHuman(Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 25;
                damage = 3;
                break;
            case 2:
                hp = 30;
                damage = 3;
                break;
            case 3:
                hp = 30;
                damage = 4;
                break;
            case 4:
                hp = 40;
                damage = 6;
                break;
        }
        human.setMaxHealth(hp);
        human.setDamage(damage);
    }

    /**
     * Устанавливает новое значение здоровья и урона для врага при повышении уровня игрока.
     * @param enemy враг
     * @param human игрок
     */
    public void NewHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.setMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.setDamage((int) enemy.getDamage() * damage / 100);
        enemy.setLevel();
    }

    /**
     * Применяет предмет к игроку по его названию. Обновляет интерфейс.
     * @param human игрок
     * @param items массив предметов
     * @param name имя используемого предмета (radioButton)
     * @param dialog окно для сообщения об ошибке
     * @param dialog1 окно инвентаря
     */
    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1":
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                if (items[2].getCount() > 0) {
                    human.setNewHealth((int) (human.getMaxHealth() * 0.05));
                    items[2].setCount(-1); 
                } else {
                    dialog.setVisible(true);
                }
                break;
        }
        if(dialog.isVisible()==false){
            dialog1.dispose();
        }
    }
}
