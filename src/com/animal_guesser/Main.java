package com.animal_guesser;

import java.util.*;

/**
 * Программа: "Угадай животное"
 * @author Pozolotin Oleg
 * @version 1.0
 * @since 2022-08-02
 * */

public class Main {

    /** Перечисление, для определения "кода" ввода*/
    enum inputCode{
        YES,
        NO,
        EXIT
    }

    /** Метод, выводящий информацию всю о животных и связях между ними
     * @param statementList
     * @param animalMap
     * */
    public static void printInfo(ArrayList<Statement> statementList,  Map<String, Animal> animalMap){
        System.out.println("Список связей:");
        for(Statement el : statementList){
            System.out.println(el);
        }
        System.out.println("-");
        Set<String> animalKeySet = animalMap.keySet();
        for(String animal: animalKeySet){
            System.out.println("Животное: \"" + animal + "\" имеет: "
                    + animalMap.get(animal).getAnimalLinks() + " связей.");
        }
        System.out.println("Количество животных: " + Animal.getCountAnimals());
        System.out.println("Количество связей: " + Animal.getCountLinks());
    }

    /** Метод, проверяющий на корректность ввод ответа, подразумевающего: да/нет/выход
     * @param stringToCheck
     * @param scanner
     * */
    public static inputCode inputVerification(String stringToCheck, Scanner scanner){
        while(true){
            switch (stringToCheck) {
                case "да":
                    return inputCode.YES;
                case "нет":
                    return inputCode.NO;
                case "выход":
                    return inputCode.EXIT;
                default:
                    System.out.println("Слово " + stringToCheck + " некорректно. Введите ответ ещё раз:");
                    stringToCheck = scanner.nextLine().toLowerCase();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // массив "утверждений" и хэш-таблица с "животными"
        ArrayList<Statement> statementList = new ArrayList<>();
        Map<String, Animal> animalMap = new HashMap<>();

        // создание двух начальных "животных" и установление связи между ними
        Animal startYesAnimal = new Animal("кот");
        startYesAnimal.incrementAnimalLinks();
        Animal startNoAnimal = new Animal("кит");
        startNoAnimal.incrementAnimalLinks();
        statementList.add(new Statement("живёт на суше", startYesAnimal, startNoAnimal));
        animalMap.put(startYesAnimal.getName(), startYesAnimal);
        animalMap.put(startNoAnimal.getName(), startNoAnimal);

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nЗагадайте животное, а я попробую отгадать...");
            // set, хранящий ключи - имена "животных"
            Set<String> animalKeySet = animalMap.keySet();
            System.out.print("База знаний: ");
            System.out.println(animalKeySet);
            // генерация случайного числа, для выбора утверждения
            int chosenStatement = (int) (Math.random() * (statementList.size()));
            System.out.println("Это животное " + statementList.get(chosenStatement).getStatementText()
                    + "? (для выхода введите - \"выход\")");
            String answer = scanner.nextLine().toLowerCase();
            String suggestedAnimalName;
            Animal suggestedAnimal;
            // проверка ввода
            inputCode inputVerificationResult = inputVerification(answer, scanner);

            // в зависимости от ответа пользователя на вопрос, компьютер в качестве предположения
            // выбирает то или иное животное (удовлетворяющее утверждению или не удовлетворяющее)
            if(inputVerificationResult == inputCode.YES){
                suggestedAnimal = statementList.get(chosenStatement).getYesAnimal();
            }
            else if (inputVerificationResult == inputCode.NO){
                suggestedAnimal = statementList.get(chosenStatement).getNoAnimal();
            }
            else{
                break;
            }
            suggestedAnimalName = suggestedAnimal.getName();
            System.out.println("Это " + suggestedAnimalName + "? (для выхода введите - \"выход\")");
            answer = scanner.nextLine().toLowerCase();
            // проверка ввода
            inputVerificationResult = inputVerification(answer, scanner);

            // если компьютер, не угадывает животное, то он предлагает ввести новое животное,
            // установив при этом связь между предложенным пользователем и загаданным компьютером животным
            if (inputVerificationResult == inputCode.NO) {
                // введение "флага" (checkFlag) для следующей проверки:
                // - нельзя устанавливать связь животного с самим собой
                // - нельзя добавлять новую связь к уже существующей паре
                boolean checkFlag = true;
                String guessedAnimalName = null;
                while(checkFlag){
                    checkFlag = false;
                    System.out.println("Какое животное Вы загадали?");
                    guessedAnimalName = scanner.nextLine();
                    if(suggestedAnimalName.equals(guessedAnimalName)){
                        System.out.println("Животное не может отличаться от себя чем-либо!\n");
                        checkFlag = true;
                    }
                    else{
                        for(Statement el : statementList){
                            if(el.statementCode() == Statement.animalPairCode(suggestedAnimalName, guessedAnimalName)){
                                System.out.println("Такая пара: " + suggestedAnimalName +
                                        " и " + guessedAnimalName + " - уже есть!\n");
                                checkFlag = true;
                                break;
                            }
                        }
                    }
                }
                // если загаданное "животное" уже есть, то оно "вытаскивается" из хэш-таблицы,
                // иначе создаётся новое "животное" и "кладётся" в хэш-таблицу,
                // затем устанавливается связь между загаданным и предложенным "животным"
                // и создаётся новый Statement
                Animal guessedAnimal = animalMap.get(guessedAnimalName);
                if(guessedAnimal == null){
                    guessedAnimal = new Animal(guessedAnimalName);
                    animalMap.put(guessedAnimalName, guessedAnimal);
                }
                System.out.println("Чем " + guessedAnimalName + " отличается от " + suggestedAnimalName + "?");
                String newStatementText = scanner.nextLine();
                statementList.add(new Statement(newStatementText, guessedAnimal, suggestedAnimal));
                // увеличение счётчиков связей
                guessedAnimal.incrementAnimalLinks();
                suggestedAnimal.incrementAnimalLinks();
            }
            else if(inputVerificationResult == inputCode.EXIT){
                break;
            }
            System.out.println("Давай сыграем ещё раз!\n");
            printInfo(statementList, animalMap);
        }
    }
}
