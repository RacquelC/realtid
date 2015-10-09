package todo;

import done.*;

public class WashingController implements ButtonListener {
    private final AbstractWashingMachine machine;
    private final WaterController waterC;
    private final TemperatureController tempC;
    private final SpinController spinC;
    private WashingProgram currentProgram;

    public WashingController(AbstractWashingMachine theMachine, double theSpeed) {
        this.machine = theMachine;
        this.waterC = new WaterController(machine, 1);
        this.tempC = new TemperatureController(machine, 1);
        this.spinC = new SpinController(machine, 1);
    }

    public void processButton(int theButton) {
        if (currentProgram != null) currentProgram.interrupt();
        switch (theButton) {
            case 1:
                currentProgram = new WashingProgram1(machine, 1, tempC, waterC, spinC);
                currentProgram.start();
                break;
            case 2:
                currentProgram = new WashingProgram2(machine, 1, tempC, waterC, spinC);
                currentProgram.start();
                break;
            case 3:
                currentProgram = new WashingProgram3(machine, 1, tempC, waterC, spinC);
                currentProgram.start();
                break;
            case 0:
                currentProgram = null;
                break;
        }
    }
}
