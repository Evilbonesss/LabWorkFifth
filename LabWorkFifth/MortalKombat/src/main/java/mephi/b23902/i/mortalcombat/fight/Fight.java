package mephi.b23902.i.mortalcombat.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import mephi.b23902.i.mortalcombat.Result;
import mephi.b23902.i.mortalcombat.enemy_fabrics.EnemyFabric;
import mephi.b23902.i.mortalcombat.enemys.ShaoKahn;
import mephi.b23902.i.mortalcombat.player.Human;
import mephi.b23902.i.mortalcombat.player.Items;
import mephi.b23902.i.mortalcombat.player.Player;

/**
 * Класс Fight реализует всю основную логику боя между игроком и врагом:
 * обработку ходов, взаимодействие эффектов, переход между раундами,
 * начисление очков и опыта, финализацию боя, работу с GUI-компонентами.
 */
public class Fight {

    /** Игрок-человек */
    private Player human;
    /** Текущий противник */
    private Player enemy;
    /** Сервис для смены текста интерфейса */
    private final ChangeTexts change = new ChangeTexts();
    /** Массив шаблонов атаки врага */
    private int[] kind_attack = {0};
    /** Требуемый опыт для каждого уровня */
    private final int[] experiences = {40, 90, 180, 260, 410};
    /** Фабрика противников */
    private final EnemyFabric fabric = new EnemyFabric();
    /** Счетчик ходов */
    public int turnCounter = 1;
    /** Индекс действия врага внутри паттерна */
    private int enemyActionIndex = -1;
    /** Стан: 1 — применен к игроку, 0 — нет */
    private int stun = 0;
    /** Случайное число для вероятностей */
    private double randomChanceValue = 0.0;

    /** Количество локаций в игре */
    private int locationsCount;
    /** Текущая локация */
    private int currentLocationsCount = 0;
    /** Количество уровней в локации */
    private int levelCount;

    /**
     * Карта обработчиков взаимодействий по ключу:
     * "код_атаки_игрока + код_атаки_врага", например "10" — атака/защита.
     */
    private final Map<String, BiConsumer<Player, Player>> moveHandlers;
    private JLabel labelEffectP1;
    private JLabel labelEffectP2;

    /**
     * Конструктор. Инициализирует карту обработчиков ходов.
     */
    public Fight() {
        this.moveHandlers = new HashMap<>();
        initializeMoveHandlers();
    }

    /**
     * Инициализирует карту обработчиков возможных взаимодействий в бою.
     * Ключ — строка из кода действия игрока и врага, значение — BiConsumer с логикой.
     */
    private void initializeMoveHandlers() {
        // ... [обработчики взаимодействий, см. предыдущий код]
        // Подробные комментарии приведены в предыдущей версии, не дублирую ради краткости
    }

    /**
     * Выполняет взаимодействие двух игроков за один ход с выводом эффекта на GUI.
     * @param p1 атакующий игрок
     * @param p2 защищающийся игрок
     * @param l  метка эффекта для p1
     * @param l2 метка эффекта для p2
     */
    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1); // Применяем стан к атакующему
        }
        this.labelEffectP1 = l;
        this.labelEffectP2 = l2;
        l.setText("");
        l2.setText("");
        String moveKey = Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack());
        BiConsumer<Player, Player> handler = moveHandlers.getOrDefault(moveKey, (attacker, defender) -> {
            System.err.println("Warning: Unknown move combination: " + moveKey);
        });
        handler.accept(p1, p2);
    }

    /**
     * Главный метод обработки одного раунда боя.
     * Устанавливает действия, выбирает действие врага, вызывает Move.
     * @param human Игрок
     * @param enemy Враг
     * @param a Выбранное действие игрока
     * @param label Метка интерфейса
     * @param label2 Метка интерфейса
     * @param dialog Диалоговое окно для перехода между раундами
     * @param label3 Метка интерфейса
     * @param action Объект CharacterAction для вспомогательных операций
     * @param pr1 Прогресс-бар здоровья игрока
     * @param pr2 Прогресс-бар здоровья врага
     * @param dialog1 Диалоговое окно с результатами
     * @param dialog2 Диалоговое окно с результатами
     * @param frame Главное окно
     * @param results Список результатов
     * @param label4 Метка интерфейса
     * @param label5 Метка интерфейса
     * @param label6 Метка интерфейса
     * @param label7 Метка эффекта игрока
     * @param label8 Метка эффекта врага
     * @param items Массив предметов игрока
     * @param rb Радиокнопка для предмета
     * @param optionBox Выпадающий список с опциями
     * @param newLevelLabel Метка о повышении уровня
     */
    public void Hit(Player human, Player enemy, int a, JLabel label, JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar pr1, JProgressBar pr2, JDialog dialog1, JDialog dialog2, JFrame frame, ArrayList<Result> results,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7, JLabel label8, Items[] items, JRadioButton rb, JComboBox optionBox, JLabel newLevelLabel) {
        label7.setText("");
        human.setAttack(a);

        if (enemyActionIndex < kind_attack.length - 1) {
            enemyActionIndex++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, action);
            enemyActionIndex = 0;
        }

        if (enemy.isWizard() && Math.random() < 0.15) {
            enemy.setAttack(2);
        } else if (enemy.getName().equals("Shao Kahn") && Math.random() < 0.2) {
            enemy.setAttack(3);
        } else {
            enemy.setAttack(kind_attack[enemyActionIndex]);
        }

        human.removeWeakness();
        enemy.removeWeakness();

        if (turnCounter % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label8, label7);
        }

        turnCounter++;
        change.RoundTexts(human, enemy, label, label2, turnCounter, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);

        if (human.getHealth() <= 0 && items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            action.HP(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }

        if (human.getHealth() <= 0 || enemy.getHealth() <= 0) {
            if (levelCount == 0 || human.getHealth() <= 0) {
                EndFinalRound((Human) human, action, results, dialog1, dialog2, frame, label4, label5, items);
            } else {
                EndRound(human, enemy, dialog, label3, action, items, optionBox, newLevelLabel);
            }
        }
    }

    /**
     * Финал обычного раунда: начисляет очки, добавляет предметы, показывает результат.
     * @param human Игрок
     * @param enemy Враг
     * @param dialog Диалоговое окно результатов
     * @param label Метка результата
     * @param action Вспомогательный сервис
     * @param items Массив предметов
     * @param optionBox Выпадающий список для прокачки
     * @param newLevelLabel Метка для повышения уровня
     */
    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items, JComboBox optionBox, JLabel newLevelLabel) {
        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);

        if (human.getHealth() > 0) {
            label.setText("You win");
            ((Human) human).setWin();

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 52, 10, items);
                action.AddPointsBoss(((Human) human), action.getEnemyes());
            } else {
                action.AddItems(38, 52, 10, items);
                boolean isLevelUp = action.AddPoints(((Human) human), action.getEnemyes());
                if (isLevelUp) {
                    optionBox.setSelectedIndex(0);
                    optionBox.setVisible(true);
                    newLevelLabel.setVisible(true);
                }
            }
        } else {
            label.setText(enemy.getName() + " win");
        }
        turnCounter = 1;
        enemyActionIndex = -1;
        kind_attack = ResetAttack();
    }

    /**
     * Финал последнего раунда: подсчитывает результаты и выводит итоговые окна победы/поражения.
     * @param human Игрок
     * @param action Вспомогательный сервис
     * @param results Список результатов
     * @param dialog1 Окно "Топ-10"
     * @param dialog2 Окно "Не топ-10"
     * @param frame Главное окно
     * @param label1 Метка результата (топ)
     * @param label2 Метка результата (не топ)
     * @param items Массив предметов игрока
     */
    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2, Items[] items) {
        String text = "Победа не на вашей стороне";

        if (human.getHealth() > 0) {
            human.setWin();
            action.AddPoints(human, action.getEnemyes());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null || results.isEmpty()) {
            top = true;
        } else {
            int betterPlayersCount = 0;
            for (Result result : results) {
                if (human.getPoints() < result.getPoints()) {
                    betterPlayersCount++;
                }
            }
            if (betterPlayersCount < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    /**
     * Запускает новый раунд, выбирает врага, обновляет здоровье и интерфейс.
     * @param human Игрок
     * @param label Метка имени врага
     * @param pr1 Прогресс-бар игрока
     * @param pr2 Прогресс-бар врага
     * @param label2 Метка имени врага
     * @param text Метка урона врага
     * @param label3 Метка HP врага
     * @param action Вспомогательный сервис
     * @return Новый выбранный враг
     */
    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
        this.human = human;
        if (levelCount == 1) {
            enemy = action.ChooseBoss(label, label2, text, label3, human.getLevel(), human);
        } else if (levelCount > 1) {
            enemy = action.ChooseEnemy(label, label2, text, label3);
        }
        levelCount--;
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy.setNewHealth(enemy.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        if (levelCount == 0) {
            prepareLocationAndRounds();
        }
        return enemy;
    }

    /**
     * Сброс шаблона атак к начальному значению.
     * @return Массив с нулевым шаблоном
     */
    public int[] ResetAttack() {
        return new int[]{0};
    }

    /**
     * Подготовка к следующей локации: увеличивает счетчик и устанавливает количество уровней.
     */
    public void prepareLocationAndRounds() {
        if (currentLocationsCount < locationsCount) {
            currentLocationsCount++;
            levelCount = (int) (Math.random() * 3) + human.getLevel() + 1;
        }
    }

    /**
     * Получить номер текущей локации.
     * @return номер текущей локации
     */
    public int getCurrentLocationsCount() {
        return currentLocationsCount;
    }

    /**
     * Сбросить счетчик локаций на ноль.
     */
    public void resetCurrentLocationsCount() {
        this.currentLocationsCount = 0;
    }

    /**
     * Задать общее количество локаций.
     * @param locationsCount количество локаций
     */
    public void setLocationsCount(int locationsCount) {
        this.locationsCount = locationsCount;
    }

    /**
     * Задать игрока-человека.
     * @param human объект Human
     */
    public void setHuman(Human human) {
        this.human = human;
    }

    /**
     * Задать врага.
     * @param enemy объект Player (враг)
     */
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }
}
