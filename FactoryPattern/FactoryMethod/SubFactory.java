package FactoryPattern.FactoryMethod;

public class SubFactory implements FactoryInterface {

    @Override
    public Operation createOperation() {
        return new Sub();
    }

}
