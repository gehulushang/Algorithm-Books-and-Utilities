package FactoryPattern.EasyFactory;

public class Factory {

    public static Operation createOperation(String name){
        Operation operation = null;
        switch (name){
            case "+": operation = new Add(); break;
            case "-": operation = new Sub(); break;
        }
        return operation;

    }
}
