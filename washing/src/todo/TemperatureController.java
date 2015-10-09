package todo;


import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;
import se.lth.cs.realtime.event.RTEvent;


public class TemperatureController extends PeriodicThread {
	private final AbstractWashingMachine machine;
	double targetTemp = 0;
	int mode = TemperatureEvent.TEMP_IDLE;

	public TemperatureController(AbstractWashingMachine mach, double speed) {
		super((long) (1000/speed)); // TODO: replace with suitable period
		this.machine = mach;
	}

	public void perform() {
		TemperatureEvent newMessage;
		if (!mailbox.isEmpty()) {
			newMessage = (TemperatureEvent)mailbox.doFetch();
			mode = newMessage.getMode();
			targetTemp = newMessage.getTemperature();
		}
		if (mode == TemperatureEvent.TEMP_SET) {
			double currentTemp = machine.getTemperature();
			if (currentTemp > targetTemp - 1.2) sendOff();
			else if (currentTemp < targetTemp - 1.8) sendOn();
		}
		else {
			sendOff();
		}
	}

	private void sendOn() {
		
		if (machine.getWaterLevel() > 0)
            machine.setHeating(true);
		
	}
	private void sendOff() {
		machine.setHeating(false);
	}
}
