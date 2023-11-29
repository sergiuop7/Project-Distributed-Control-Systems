package Project3;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;

public class Monitor {

	public static void main(String[] args) {
	
		PetriNet monitor = new PetriNet();
		monitor.PetriNetName = "Monitor Petri";
		monitor.NetworkPort = 1081;
		
		DataString p1 = new DataString();
		p1.SetName("in");
		monitor.PlaceList.add(p1);

		DataString p2 = new DataString();
		p2.SetName("P0");
		p2.SetValue("signal");
		monitor.PlaceList.add(p2);
		
		DataString p3 = new DataString();
		p3.SetName("P1");
		monitor.PlaceList.add(p3);
		
		// T1
		
		PetriTransition t1 = new PetriTransition(monitor);
		t1.TransitionName = "T0";
		t1.InputPlaceName.add("in");
		t1.InputPlaceName.add("P0");

		Condition T1Ct1 = new Condition(t1, "in", TransitionCondition.NotNull);
		Condition T1Ct2 = new Condition(t1, "P0", TransitionCondition.NotNull);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition= T1Ct1;
		grdT1.Activations.add(new Activation(t1, "in", TransitionOperation.Copy, "P1"));
		grdT1.Activations.add(new Activation(t1, "P0", TransitionOperation.Copy, "P0"));
		t1.GuardMappingList.add(grdT1);
		
		t1.Delay = 0;
		monitor.Transitions.add(t1);
		
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = monitor;
		frame.setVisible(true);
	}
}
