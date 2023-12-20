package Project5;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataInteger;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class ClosedLoopController {
	
	public static void main (String []args) {
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Controller";
		pn.SetName("Controller");
		pn.NetworkPort = 1081;
		
		DataString ini = new DataString();
		//ini.Printable = false;
		ini.SetName("ini");
		ini.SetValue("red");
		pn.ConstantPlaceList.add(ini);
		
		DataString red = new DataString();
		//red.Printable = false;
		red.SetName("red");
		red.SetValue("red");
		pn.ConstantPlaceList.add(red);
		
		DataString green = new DataString();
		//green.Printable = false;
		green.SetName("green");
		green.SetValue("green");
		pn.ConstantPlaceList.add(green);
		
		DataString yellow = new DataString();
		//yellow.Printable = false;
		yellow.SetName("yellow");
		yellow.SetValue("yellow");
		pn.ConstantPlaceList.add(yellow);
		
		DataString p1 = new DataString();
		p1.SetName("r1r2r3r4");
		p1.SetValue("signal");
		pn.PlaceList.add(p1);
		
		DataString p2 = new DataString();
		p2.SetName("g1r2r3r4");
		pn.PlaceList.add(p2);
		
		DataString p3 = new DataString();
		p3.SetName("y1r2r3r4");
		pn.PlaceList.add(p3);
		
		DataString p4 = new DataString();
		p4.SetName("r1g2r3r4");
		pn.PlaceList.add(p4);
		
		DataString p5 = new DataString();
		p5.SetName("r1y2r3r4");
		pn.PlaceList.add(p5);
		
		DataString p6 = new DataString();
		p6.SetName("r1r2g3r4");
		pn.PlaceList.add(p6);
		
		DataString p7 = new DataString();
		p7.SetName("r1r2y3r4");
		pn.PlaceList.add(p7);
		
		DataString p8 = new DataString();
		p8.SetName("r1r2r3g4");
		pn.PlaceList.add(p8);
		
		DataString p9 = new DataString();
		p9.SetName("r1r2r3y4");
		pn.PlaceList.add(p9);
		
		DataTransfer p10 = new DataTransfer();
		p10.SetName("OP1");
		p10.Value = new TransferOperation("localhost", "1080" , "P_TL1");
		pn.PlaceList.add(p10);
		
		DataTransfer p11 = new DataTransfer();
		p11.SetName("OP2");
		p11.Value = new TransferOperation("localhost", "1080" , "P_TL2");
		pn.PlaceList.add(p11);
		
		DataTransfer p12 = new DataTransfer();
		p12.SetName("OP3");
		p12.Value = new TransferOperation("localhost", "1080" , "P_TL3");
		pn.PlaceList.add(p12);
		
		DataTransfer p13 = new DataTransfer();
		p13.SetName("OP4");
		p13.Value = new TransferOperation("localhost", "1080" , "P_TL4");
		pn.PlaceList.add(p13);
		
		DataString p14 = new DataString();
		p14.SetName("in1");
		pn.PlaceList.add(p14);
		
		DataString p15 = new DataString();
		p15.SetName("in2");
		pn.PlaceList.add(p15);
		
		DataString p16 = new DataString();
		p16.SetName("in3");
		pn.PlaceList.add(p16);
		
		DataString p17 = new DataString();
		p17.SetName("in4");
		pn.PlaceList.add(p17);
		
		 DataInteger Five = new DataInteger();
        Five.SetName("Five");
        Five.SetValue(5);
        pn.ConstantPlaceList.add(Five);
        
        DataInteger Ten = new DataInteger();
        Ten.SetName("Ten");
        Ten.SetValue(10);
        pn.ConstantPlaceList.add(Ten);
		
		
		//----------------------------iniT------------------------------------
		PetriTransition iniT = new PetriTransition(pn);
		iniT.TransitionName = "iniT";
		
		Condition iniTCt1 = new Condition(iniT, "ini", TransitionCondition.NotNull);
	
		GuardMapping grdiniT = new GuardMapping();
		grdiniT.condition= iniTCt1;
		
		grdiniT.Activations.add(new Activation(iniT, "ini", TransitionOperation.SendOverNetwork, "OP1"));
		grdiniT.Activations.add(new Activation(iniT, "ini", TransitionOperation.SendOverNetwork, "OP2"));
		grdiniT.Activations.add(new Activation(iniT, "ini", TransitionOperation.SendOverNetwork, "OP3"));
		grdiniT.Activations.add(new Activation(iniT, "ini", TransitionOperation.SendOverNetwork, "OP4"));
		grdiniT.Activations.add(new Activation(iniT, "", TransitionOperation.MakeNull, "ini"));
		
		iniT.GuardMappingList.add(grdiniT);
	
		iniT.Delay = 0;
		pn.Transitions.add(iniT);
		
		
		
		//----------------------------T1------------------------------------
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "T1";
		t1.InputPlaceName.add("r1r2r3r4");
		
		
		Condition T1Ct1 = new Condition(t1, "r1r2r3r4", TransitionCondition.NotNull);
	
		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition= T1Ct1;
		grdT1.Activations.add(new Activation(t1, "r1r2r3r4", TransitionOperation.Move, "g1r2r3r4"));
		grdT1.Activations.add(new Activation(t1, "green", TransitionOperation.SendOverNetwork, "OP1"));
		t1.GuardMappingList.add(grdT1);
	
		t1.Delay = 5;
		pn.Transitions.add(t1);
		
		//----------------------------T2------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "T2";
		t2.InputPlaceName.add("g1r2r3r4");
		t2.InputPlaceName.add("in1");
		
		
		Condition T2Ct1 = new Condition(t2, "g1r2r3r4", TransitionCondition.NotNull);
		Condition T2Ct2 = new Condition(t2, "in1", TransitionCondition.IsNull);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);
		
		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition= T2Ct1;
		grdT2.Activations.add(new Activation(t2, "g1r2r3r4", TransitionOperation.Move, "y1r2r3r4"));
		grdT2.Activations.add(new Activation(t2, "yellow", TransitionOperation.SendOverNetwork, "OP1"));
		grdT2.Activations.add(new Activation(t2, "Five", TransitionOperation.DynamicDelay,""));
		
		
		t2.GuardMappingList.add(grdT2);
	
		Condition T2Ct3 = new Condition(t2, "g1r2r3r4", TransitionCondition.NotNull);
		Condition T2Ct4 = new Condition(t2, "in1", TransitionCondition.NotNull);
		T2Ct3.SetNextCondition(LogicConnector.AND, T2Ct4);
		
		GuardMapping grdT21 = new GuardMapping();
		grdT21.condition= T2Ct3;
		grdT21.Activations.add(new Activation(t2, "g1r2r3r4", TransitionOperation.Move, "y1r2r3r4"));
		grdT21.Activations.add(new Activation(t2, "Ten", TransitionOperation.DynamicDelay,""));
		grdT21.Activations.add(new Activation(t2, "yellow", TransitionOperation.SendOverNetwork, "OP1"));
		
		
		t2.GuardMappingList.add(grdT21);
		
		t2.Delay = 5;	
		
		pn.Transitions.add(t2);

		//----------------------------T3------------------------------------
		PetriTransition t3 = new PetriTransition(pn);
		t3.TransitionName = "T3";
		t3.InputPlaceName.add("y1r2r3r4");
		
		
		
		Condition T3Ct1 = new Condition(t2, "y1r2r3r4", TransitionCondition.NotNull);
	
		GuardMapping grdT3 = new GuardMapping();
		grdT3.condition= T3Ct1;
		grdT3.Activations.add(new Activation(t3, "y1r2r3r4", TransitionOperation.Move, "r1g2r3r4"));
		grdT3.Activations.add(new Activation(t3, "red", TransitionOperation.SendOverNetwork, "OP1"));
		grdT3.Activations.add(new Activation(t3, "green", TransitionOperation.SendOverNetwork, "OP2"));
				
		t3.GuardMappingList.add(grdT3);
	
		t3.Delay = 5;
		pn.Transitions.add(t3);
		
		
		//----------------------------T4------------------------------------
		PetriTransition t4 = new PetriTransition(pn);
		t4.TransitionName = "T4";
		t4.InputPlaceName.add("r1g2r3r4");
		t4.InputPlaceName.add("in2");
		
		
		Condition T4Ct1 = new Condition(t4, "r1g2r3r4", TransitionCondition.NotNull);
		Condition T4Ct2 = new Condition(t4, "in2", TransitionCondition.IsNull);
		T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);
		
	
		GuardMapping grdT4 = new GuardMapping();
		grdT4.condition= T4Ct1;
		grdT4.Activations.add(new Activation(t4, "r1g2r3r4", TransitionOperation.Move, "r1y2r3r4"));
		grdT4.Activations.add(new Activation(t4, "yellow", TransitionOperation.SendOverNetwork, "OP2"));
		grdT4.Activations.add(new Activation(t4, "Five", TransitionOperation.DynamicDelay,""));
		
		t4.GuardMappingList.add(grdT4);
	
		Condition T4Ct3 = new Condition(t4, "r1g2r3r4", TransitionCondition.NotNull);
		Condition T4Ct4 = new Condition(t4, "in2", TransitionCondition.NotNull);
		T4Ct3.SetNextCondition(LogicConnector.AND, T4Ct4);
		
		GuardMapping grdT41 = new GuardMapping();
		grdT41.condition= T4Ct3;
		grdT41.Activations.add(new Activation(t4, "r1g2r3r4", TransitionOperation.Move, "r1y2r3r4"));
		grdT41.Activations.add(new Activation(t4, "Ten", TransitionOperation.DynamicDelay,""));
		grdT41.Activations.add(new Activation(t4, "yellow", TransitionOperation.SendOverNetwork, "OP2"));
		
		t4.GuardMappingList.add(grdT41);
		
		t4.Delay = 5;
		pn.Transitions.add(t4);
		
		
		//----------------------------T5------------------------------------
		PetriTransition t5 = new PetriTransition(pn);
		t5.TransitionName = "T5";
		t5.InputPlaceName.add("r1y2r3r4");
		
		
		Condition T5Ct1 = new Condition(t5, "r1y2r3r4", TransitionCondition.NotNull);
	
		GuardMapping grdT5 = new GuardMapping();
		grdT5.condition= T5Ct1;
		grdT5.Activations.add(new Activation(t5, "r1y2r3r4", TransitionOperation.Move, "r1r2g3r4"));
		grdT5.Activations.add(new Activation(t5, "red", TransitionOperation.SendOverNetwork, "OP2"));
		grdT5.Activations.add(new Activation(t5, "green", TransitionOperation.SendOverNetwork, "OP3"));
	
		
		t5.GuardMappingList.add(grdT5);
	
		t5.Delay = 5;
		pn.Transitions.add(t5);
		
		//----------------------------T6------------------------------------
		PetriTransition t6 = new PetriTransition(pn);
		t6.TransitionName = "T6";
		t6.InputPlaceName.add("r1r2g3r4");
		t6.InputPlaceName.add("in3");
				
		Condition T6Ct1 = new Condition(t6, "r1r2g3r4", TransitionCondition.NotNull);
		Condition T6Ct2 = new Condition(t6, "in3", TransitionCondition.IsNull);
		T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);
		
		GuardMapping grdT6 = new GuardMapping();
		
		grdT6.condition= T6Ct1;
		grdT6.Activations.add(new Activation(t6, "r1r2g3r4", TransitionOperation.Move, "r1r2y3r4"));
		grdT6.Activations.add(new Activation(t6, "yellow", TransitionOperation.SendOverNetwork, "OP3"));
		grdT6.Activations.add(new Activation(t6, "Five", TransitionOperation.DynamicDelay,""));
	
				
		t6.GuardMappingList.add(grdT6);
		
		Condition T6Ct3 = new Condition(t6, "r1r2g3r4", TransitionCondition.NotNull);
		Condition T6Ct4 = new Condition(t6, "in3", TransitionCondition.NotNull);
		T6Ct3.SetNextCondition(LogicConnector.AND, T6Ct4);
		
		GuardMapping grdT61 = new GuardMapping();
		
		grdT61.condition= T6Ct3;
		grdT61.Activations.add(new Activation(t6, "r1r2g3r4", TransitionOperation.Move, "r1r2y3r4"));
		grdT61.Activations.add(new Activation(t6, "Ten", TransitionOperation.DynamicDelay,""));
		grdT61.Activations.add(new Activation(t6, "yellow", TransitionOperation.SendOverNetwork, "OP3"));
		
		t6.GuardMappingList.add(grdT61);
			
		t6.Delay = 5;
		pn.Transitions.add(t6);
	
		//----------------------------T7------------------------------------
		PetriTransition t7 = new PetriTransition(pn);
		t7.TransitionName = "T7";
		t7.InputPlaceName.add("r1r2y3r4");
			
				
		Condition T7Ct1 = new Condition(t7, "r1r2y3r4", TransitionCondition.NotNull);
		
		GuardMapping grdT7 = new GuardMapping();
		
		grdT7.condition= T7Ct1;
		grdT7.Activations.add(new Activation(t7, "r1r2y3r4", TransitionOperation.Move, "r1r2r3g4"));
		grdT7.Activations.add(new Activation(t7, "red", TransitionOperation.SendOverNetwork, "OP3"));
		grdT7.Activations.add(new Activation(t7, "green", TransitionOperation.SendOverNetwork, "OP4"));
				
		t7.GuardMappingList.add(grdT7);
			
		t7.Delay = 5;
		pn.Transitions.add(t7);
	
		//----------------------------T8------------------------------------
		PetriTransition t8 = new PetriTransition(pn);
		t8.TransitionName = "T8";
		t8.InputPlaceName.add("r1r2r3g4");
		t8.InputPlaceName.add("in4");
				
		Condition T8Ct1 = new Condition(t8, "r1r2r3g4", TransitionCondition.NotNull);
		Condition T8Ct2 = new Condition(t8, "in4", TransitionCondition.IsNull);
		T8Ct1.SetNextCondition(LogicConnector.AND, T8Ct2);
		
		GuardMapping grdT8 = new GuardMapping();
		
		grdT8.condition= T8Ct1;
		grdT8.Activations.add(new Activation(t8, "r1r2r3g4", TransitionOperation.Move, "r1r2r3y4"));
		grdT8.Activations.add(new Activation(t8, "yellow", TransitionOperation.SendOverNetwork, "OP4"));
		grdT8.Activations.add(new Activation(t8, "Five", TransitionOperation.DynamicDelay,""));
				
		t8.GuardMappingList.add(grdT8);
		
		Condition T8Ct3 = new Condition(t8, "r1r2r3g4", TransitionCondition.NotNull);
		Condition T8Ct4 = new Condition(t8, "in4", TransitionCondition.NotNull);
		T8Ct3.SetNextCondition(LogicConnector.AND, T8Ct4);
		
		GuardMapping grdT81 = new GuardMapping();
		
		grdT81.condition= T8Ct3;
		grdT81.Activations.add(new Activation(t8, "r1r2r3g4", TransitionOperation.Move, "r1r2r3y4"));
		grdT81.Activations.add(new Activation(t8, "Ten", TransitionOperation.DynamicDelay,""));
		grdT81.Activations.add(new Activation(t8, "yellow", TransitionOperation.SendOverNetwork, "OP4"));
				
		t8.GuardMappingList.add(grdT81);
			
		t8.Delay = 5;
		pn.Transitions.add(t8);
		
		
		//----------------------------T9------------------------------------
		PetriTransition t9 = new PetriTransition(pn);
		t9.TransitionName = "T9";
		t9.InputPlaceName.add("r1r2r3y4");
			
				
		Condition T9Ct1 = new Condition(t9, "r1r2r3y4", TransitionCondition.NotNull);
		
		GuardMapping grdT9 = new GuardMapping();
		
		grdT9.condition= T9Ct1;
		grdT9.Activations.add(new Activation(t9, "r1r2r3y4", TransitionOperation.Move, "r1r2r3r4"));
		grdT9.Activations.add(new Activation(t9, "red", TransitionOperation.SendOverNetwork, "OP4"));
				
		t9.GuardMappingList.add(grdT9);
			
		t9.Delay = 5;
		pn.Transitions.add(t9);
		
		// -------------------------------------------------------------------------------------
		// ----------------------------PN Start-------------------------------------------------
		// -------------------------------------------------------------------------------------
	
		System.out.println("Exp1 started \n ------------------------------");
		pn.Delay = 2000;
		// pn.Start();
	
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}
}
