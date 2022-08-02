package com.animal_guesser;

public class Statement {
    private final String statementText;
    private final Animal yesAnimal;
    private final Animal noAnimal;

    public static int animalPareCode(String firstAnimalName, String secondAnimalName){
        return firstAnimalName.hashCode() + secondAnimalName.hashCode();
    }

    public Statement(String text, Animal yesAnimal, Animal noAnimal){
        this.statementText = text;
        this.yesAnimal = yesAnimal;
        this.noAnimal = noAnimal;
    }

    public String getStatementText() {
        return statementText;
    }
    public Animal getYesAnimal() {
        return yesAnimal;
    }
    public Animal getNoAnimal() {
        return noAnimal;
    }

    public int statementCode(){
        return yesAnimal.getName().hashCode() + noAnimal.getName().hashCode();
    }

    @Override
    public String toString(){
        return yesAnimal.getName() + " в отличие от " + noAnimal.getName() + " " + statementText;
    }
}
