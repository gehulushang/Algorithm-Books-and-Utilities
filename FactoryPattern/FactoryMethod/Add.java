package FactoryPattern.FactoryMethod;

public class Add implements Operation {
    @Override
    public double getResult(double a, double b) {

        return a+b;

    }
}
