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

public class Fight {

    private Player human;
    private Player enemy;
    private final ChangeTexts change = new ChangeTexts();
    private int[] kind_attack = {0};
    private final int[] experiences = {40, 90, 180, 260, 410};
    private final EnemyFabric fabric = new EnemyFabric();
    public int turnCounter = 1;
    private int enemyActionIndex = -1;
    private int stun = 0;
    private double randomChanceValue = 0.0;

    private int locationsCount;
    private int currentLocationsCount = 0;
    private int levelCount;
    
    // Карта для хранения обработчиков всех возможных ходов
    private final Map<String, BiConsumer<Player, Player>> moveHandlers;
    private JLabel labelEffectP1;
    private JLabel labelEffectP2;

    public Fight() {
        this.moveHandlers = new HashMap<>();
        initializeMoveHandlers();
    }
    
    /**
     * Инициализирует карту всех возможных взаимодействий в бою.
     * Каждое взаимодействие - это лямбда-выражение, принимающее атакующего (p1) и защищающегося (p2).
     */
    private void initializeMoveHandlers() {
        // Атака vs Защита -> "10"
        moveHandlers.put("10", (p1, p2) -> {
            randomChanceValue = Math.random();
            if (p1 instanceof ShaoKahn && randomChanceValue < 0.15) {
                p2.setHealth(-(int) (p1.getDamage() * 0.5));
                labelEffectP2.setText("Your block is broken");
            } else {
                p1.setHealth(-(int) (p2.getDamage() * 0.5));
                labelEffectP2.setText(p2.getName() + " counterattacked");
            }
        });

        // Атака vs Атака -> "11"
        moveHandlers.put("11", (p1, p2) -> {
            p2.setHealth(-p1.getDamage());
            labelEffectP2.setText(p1.getName() + " attacked");
        });

        // Защита vs Защита -> "00"
        moveHandlers.put("00", (p1, p2) -> {
            randomChanceValue = Math.random();
            if (randomChanceValue <= 0.5) {
                stun = 1; // Стан получает тот, чей ход следующий
            }
            labelEffectP2.setText("Both defended themselves");
        });

        // Защита vs Атака -> "01"
        moveHandlers.put("01", (p1, p2) -> labelEffectP2.setText(p1.getName() + " didn't attack"));

        // Стан vs Защита -> "-10"
        moveHandlers.put("-10", (p1, p2) -> {
            labelEffectP1.setText(p1.getName() + " was stunned");
            stun = 0;
            labelEffectP2.setText(p2.getName() + " didn't attack");
        });

        // Стан vs Атака -> "-11"
        moveHandlers.put("-11", (p1, p2) -> {
            p1.setHealth(-p2.getDamage());
            labelEffectP1.setText(p1.getName() + " was stunned");
            stun = 0;
            labelEffectP2.setText(p2.getName() + " attacked");
        });

        // Ослабление vs Защита -> "20"
        BiConsumer<Player, Player> weakenVsDefend = (p1, p2) -> {
            if (Math.random() < 0.75) {
                p2.setWeakness(p1.getLevel());
                labelEffectP1.setText(p1.getName() + " used Weakness");
                labelEffectP2.setText(p2.getName() + " is weakened");
            } else {
                labelEffectP1.setText(p1.getName() + " tried to weaken, but failed!");
            }
        };
        moveHandlers.put("20", weakenVsDefend);
        moveHandlers.put("2-1", weakenVsDefend);

        // Атака vs Ослабление -> "12"
        moveHandlers.put("12", (p1, p2) -> {
            p2.setHealth(-p1.getDamage() * 1.15);
            labelEffectP1.setText(p1.getName() + " attacked");
            labelEffectP2.setText("Failed to weak opponent");
        });
        
        // Ослабление vs Атака -> "21"
        moveHandlers.put("21", (p1, p2) -> {
            p1.setHealth(-p2.getDamage() * 1.15);
            labelEffectP1.setText(p1.getName() + " attacked");
            labelEffectP2.setText("Failed to weak opponent");
        });
        
        // Регенерация vs Защита -> "30"
        BiConsumer<Player, Player> regenVsDefend = (p1, p2) -> {
            p1.setHealth((p1.getMaxHealth() - p1.getHealth()) * 0.5);
            labelEffectP1.setText(p1.getName() + " regenerated");
        };
        moveHandlers.put("30", regenVsDefend);
        moveHandlers.put("3-1", regenVsDefend); 
        
        // Атака vs Регенерация -> "13"
        moveHandlers.put("13", (p1, p2) -> {
            p2.setHealth(-p1.getDamage() * 2);
            labelEffectP1.setText(p1.getName() + " attacked");
            labelEffectP2.setText("Failed to regenerate");
        });

        // Регенерация vs Атака -> "31"
        moveHandlers.put("31", (p1, p2) -> {
            p1.setHealth(-p2.getDamage() * 2);
            labelEffectP2.setText(p2.getName() + " attacked");
            labelEffectP1.setText("Failed to regenerate");
        });

        // Ослабление vs Регенерация -> "23"
        moveHandlers.put("23", (p1, p2) -> {
            p2.setWeakness(p1.getLevel());
            p2.setHealth((p2.getMaxHealth() - p2.getHealth()) * 0.5);
            labelEffectP1.setText(p1.getName() + " used Weakness");
            labelEffectP2.setText(p2.getName() + " regenerated");
        });
        
        // Регенерация vs Ослабление -> "32"
        moveHandlers.put("32", (p1, p2) -> {
            p1.setWeakness(p2.getLevel());
            p1.setHealth((p1.getMaxHealth() - p1.getHealth()) * 0.5);
            labelEffectP2.setText(p2.getName() + " used Weakness");
            labelEffectP1.setText(p1.getName() + " regenerated");
        });
        
        // Защита vs Ослабление -> "02"
        moveHandlers.put("02", (p1, p2) -> {
            if (Math.random() < 0.75) {
                p1.setWeakness(p1.getLevel());
                labelEffectP2.setText(p2.getName() + " used Weakness");
                labelEffectP1.setText(p1.getName() + " is weakened");
            }
        });
        
        // Стан(-1) vs Ослабление(2) -> "-12"
        moveHandlers.put("-12", (p1, p2) -> {
             if (Math.random() < 0.75) {
                p1.setWeakness(p1.getLevel());
                labelEffectP2.setText(p2.getName() + " used Weakness");
                labelEffectP1.setText(p1.getName() + " is weakened");
            }
        });
        
        // Защита vs Регенерация -> "03"
        moveHandlers.put("03", (p1, p2) -> {
            p2.setHealth((p2.getMaxHealth() - p2.getHealth()) * 0.5);
            labelEffectP2.setText(p2.getName() + " regenerated");
        });
        
        // Стан vs Регенерация -> "-13"
        moveHandlers.put("-13", (p1, p2) -> {
             p2.setHealth((p2.getMaxHealth() - p2.getHealth()) * 0.5);
             labelEffectP2.setText(p2.getName() + " regenerated");
        });
    }

    /**
     * Основной метод, обрабатывающий взаимодействие двух игроков за один ход.
     * @param p1 Игрок, который делает ход (атакующий)
     * @param p2 Игрок, который реагирует на ход (защищающийся)
     * @param l Метка для вывода эффектов на p1
     * @param l2 Метка для вывода эффектов на p2
     */
    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1); // Накладываем стан на того, кто ходит сейчас
        }

        this.labelEffectP1 = l;
        this.labelEffectP2 = l2;
        l.setText("");
        l2.setText("");

        String moveKey = Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack());

        // Находим нужный обработчик в карте и выполняем его.
        // getOrDefault() безопасно вернет "пустой" обработчик, если комбинация не найдена.
        BiConsumer<Player, Player> handler = moveHandlers.getOrDefault(moveKey, (attacker, defender) -> {
            System.err.println("Warning: Unknown move combination: " + moveKey);
        });

        handler.accept(p1, p2);
    }
    
    /**
     * Точка входа для обработки хода. Определяет действия игроков и вызывает Move.
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

    public int[] ResetAttack() {
        return new int[]{0};
    }
    
    public void prepareLocationAndRounds() {
        if (currentLocationsCount < locationsCount) {
            currentLocationsCount++;
            levelCount = (int) (Math.random() * 3) + human.getLevel() + 1;
        }
    }

    public int getCurrentLocationsCount() {
        return currentLocationsCount;
    }

    public void resetCurrentLocationsCount() {
        this.currentLocationsCount = 0;
    }

    public void setLocationsCount(int locationsCount) {
        this.locationsCount = locationsCount;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }
}