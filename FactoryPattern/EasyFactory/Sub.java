package FactoryPattern.EasyFactory;

public class Sub implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a-b;
    }
}
