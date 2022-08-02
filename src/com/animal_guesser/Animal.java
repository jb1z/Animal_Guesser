package com.animal_guesser;

public class Animal {
    /** Счётчик количества животных*/
    private static int countAnimals = 0;
    /** Счётчик количества связей между животными*/
    private static int countLinks = 0;

    /** Имя животного*/
    private final String animalName;
    /** Количество связей животного*/
    private int animalLinks = 0;

    /** Конструктор принимающий на выход строку - имя животного,
     * также здесь увеличивается статический счётчик общего числа экземпляром этого класса.
     * @param animalName */
    public Animal(String animalName){
        this.animalName = animalName;
        countAnimals++;
    }

    /** Метод, возвращающий общее количество животных
     * @return countAnimals */
    public static int getCountAnimals(){
        return countAnimals;
    }
    /** Метод, возвращающий общее количество связей животных
     * @return countLinks */
    public static int getCountLinks(){
        return countLinks;
    }

    /** Метод, возвращающий имя животного
     * @return animalName */
    public String getName(){
        return animalName;
    }
    /** Метод, возвращающий имя количество связей животного
     * @return animalLinks */
    public int getAnimalLinks(){
        return animalLinks;
    }

    /** Метод, увеличивающий общее количество связей, при каждой новой появляющейся связи конкретного животного
     * */
    public void incrementAnimalLinks(){
        animalLinks++;
        countLinks++;
    }
}
