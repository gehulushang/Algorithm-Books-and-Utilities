package FactoryPattern.FactoryMethod;

public class Sub implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a-b;
    }
}
