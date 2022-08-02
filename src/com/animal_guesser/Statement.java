package com.animal_guesser;

public class Statement {
    /** Утверждение, связывающее двух "животных"*/
    private final String statementText;
    /** Животное, удовлетворяющее утверждению*/
    private final Animal yesAnimal;
    /** Животное, не удовлетворяющее утверждению*/
    private final Animal noAnimal;

    /** Статический метод, принимающий две строки, возвращает сумму их хэш-кодов
     * @param firstAnimalName
     * @param secondAnimalName
     * @return int сумма хэш-кодов двух строк */
    public static int animalPairCode(String firstAnimalName, String secondAnimalName){
        return firstAnimalName.hashCode() + secondAnimalName.hashCode();
    }

    /** Конструктор, принимающий текст утверждения и
     * двух "животных", логически связанных утверждением
     * @param text
     * @param yesAnimal
     * @param noAnimal */
    public Statement(String text, Animal yesAnimal, Animal noAnimal){
        this.statementText = text;
        this.yesAnimal = yesAnimal;
        this.noAnimal = noAnimal;
    }

    /** Метод, возвращающий текст утверждения
     * @return statementText */
    public String getStatementText() {
        return statementText;
    }
    /** Метод, возвращающий имя "животного", удовлетворяющего утверждению
     * @return yesAnimal */
    public Animal getYesAnimal() {
        return yesAnimal;
    }
    /** Метод, возвращающий имя "животного", не удовлетворяющего утверждению
     * @return noAnimal */
    public Animal getNoAnimal() {
        return noAnimal;
    }

    /** Метод, возвращающий сумму хэш-кодов, связанных животных
     * @return сумма хэш-кодов имён "животных", связанных утверждением*/
    public int statementCode(){
        return yesAnimal.getName().hashCode() + noAnimal.getName().hashCode();
    }

    @Override
    public String toString(){
        return yesAnimal.getName() + " в отличие от " + noAnimal.getName() + " " + statementText;
    }
}
