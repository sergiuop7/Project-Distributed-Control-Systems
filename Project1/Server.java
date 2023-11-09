package Project1;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
       
        PetriNet serverPN = new PetriNet();
        serverPN.PetriNetName = "Server";
        serverPN.NetworkPort = 1081;

        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        p0.SetValue(1.0f);
        serverPN.PlaceList.add(p0);

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        serverPN.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("p2");
        serverPN.PlaceList.add(p2);

        DataTransfer p3 = new DataTransfer();
        p3.SetName("p3");
        serverPN.PlaceList.add(p3);
        p3.Value = new TransferOperation("localhost", "1080", "p5");
        
        DataFloat subConstantValue1 = new DataFloat();
		subConstantValue1.SetName("subConstantValue1");
		subConstantValue1.SetValue(0.1f);
		serverPN.ConstantPlaceList.add(subConstantValue1);

        // t1
        PetriTransition t1 = new PetriTransition(serverPN);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p0");
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t1, "p0", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p1", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);
        
        ArrayList<String> inputList = new ArrayList<String>();
        inputList.add("p1");
        inputList.add("subConstantValue1");
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
//        grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.Power_Float, "p2"));
        grdT1.Activations.add(new Activation(t1, inputList, TransitionOperation.Prod, "p2"));
        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        serverPN.Transitions.add(t1);
        
        // t2
        PetriTransition t2 = new PetriTransition(serverPN);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");

        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.Move, "p0"));
        grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.SendOverNetwork, "p3"));
        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        serverPN.Transitions.add(t2);

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = serverPN;
        frame.setVisible(true);
    }

}
