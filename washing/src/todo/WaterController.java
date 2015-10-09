package todo;


import se.lth.cs.realtime.*;
import done.AbstractWashingMachine;


public class WaterController extends PeriodicThread {
	private final AbstractWashingMachine machine;
	// TODO: add suitable attributes
	int mode = WaterEvent.WATER_IDLE;
	int MAX_FILL = 10;
	double targetLevel = 0;

	public WaterController(AbstractWashingMachine mach, double speed) {
		super((long) (1000/speed)); // TODO: replace with suitable period
		this.machine = mach;
	}

	public void perform() {
		if (!mailbox.isEmpty()) {
			WaterEvent newMessage = (WaterEvent)mailbox.doFetch();
			mode = newMessage.getMode();
			targetLevel = newMessage.getLevel();
		}
		if (mode == WaterEvent.WATER_IDLE) {
			sendIdle();
		}
		else if (mode == WaterEvent.WATER_FILL) {
		}
	}

	private void sendIdle() {
		machine.setDrain(false);
		machine.setFill(false);
	}
}
