package todo;

import done.AbstractWashingMachine;

/**
 * Created by von on 2015-10-09.
 */
public class WashingProgram1 extends WashingProgram {
    public WashingProgram1(AbstractWashingMachine machine, int i, TemperatureController tempC, WaterController waterC, SpinController spinC) {
        super(machine, i, tempC, waterC, spinC);
    }

    @Override
    protected void wash() throws InterruptedException {

    }
}
