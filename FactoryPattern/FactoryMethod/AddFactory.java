package FactoryPattern.FactoryMethod;

public class AddFactory implements FactoryInterface {
    @Override
    public Operation createOperation() {
        return new Add();

    }
}
