package process.visitor;

import process.communication.CommunicationSystem;

public class CommunicationVisitor implements ManagerVisitor<Void> {
	private CommunicationSystem communicationSystem;

	public CommunicationVisitor(CommunicationSystem communicationSystem) {
		this.communicationSystem = communicationSystem;
	}



}
