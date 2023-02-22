package main;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Functions {
	String sizeOrder;
	static DefaultListModel<String> orderLists = new DefaultListModel<String>();
	static ArrayList<Double> order = new ArrayList<Double>();
	public static double orderTotalCost = 0;
	static double currOrderTotal;
	
	public static void showTotal() {
		System.out.println(order);
	}
	
	public static void coffee() {
		String orderGetSize = UserInterface.size;
		
		if (orderGetSize == Resources.small) {
			currOrderTotal = 100;
		} else if (orderGetSize ==  Resources.medium) {
			currOrderTotal = 150;
		} else if (orderGetSize ==  Resources.large) {
			currOrderTotal = 200;
		};
		
		double finalCurrOrderTotal = currOrderTotal * UserInterface.qty;
		orderTotalCost += finalCurrOrderTotal;
		orderLists.addElement(Resources.coffee + " " + orderGetSize + " : " + finalCurrOrderTotal + " - " + UserInterface.qty + "X ");
		order.add(finalCurrOrderTotal);
	}
	
	public static void juice() {
		String orderGetSize = UserInterface.size;
		
		if (orderGetSize == Resources.small) {
			currOrderTotal = 100;
		} else if (orderGetSize == Resources.medium) {
			currOrderTotal = 150;
		} else if (orderGetSize == Resources.large) {
			currOrderTotal = 200;
		};
		
		double finalCurrOrderTotal = currOrderTotal * UserInterface.qty;
		orderTotalCost += finalCurrOrderTotal;
		orderLists.addElement(Resources.juice + " " + orderGetSize + " : " + finalCurrOrderTotal + " - " + UserInterface.qty + "X ");
		order.add(finalCurrOrderTotal);
	}
	
	public static void cheesecake() {
		String orderGetSize = UserInterface.size;
		
		if (orderGetSize == Resources.small) {
			currOrderTotal = 70;
		} else if (orderGetSize ==  Resources.medium) {
			currOrderTotal = 90;
		} else if (orderGetSize == Resources.large) {
			currOrderTotal = 120;
		};
		
		double finalCurrOrderTotal = currOrderTotal * UserInterface.qty;
		orderTotalCost += finalCurrOrderTotal;
		orderLists.addElement(Resources.cheesecake + " " + orderGetSize + " : " + finalCurrOrderTotal + " - " + UserInterface.qty + "X ");
		order.add(finalCurrOrderTotal);
	}
	
	public static void spongecake() {
		String orderGetSize = UserInterface.size;
		
		if (orderGetSize == Resources.small) {
			currOrderTotal = 50;
		} else if (orderGetSize ==  Resources.medium) {
			currOrderTotal = 70;
		} else if (orderGetSize == Resources.large) {
			currOrderTotal = 100;
		};
		
		double finalCurrOrderTotal = currOrderTotal * UserInterface.qty;
		orderTotalCost += finalCurrOrderTotal;
		orderLists.addElement(Resources.spongecake + " " + orderGetSize + " : " + finalCurrOrderTotal + " - " + UserInterface.qty + "X ");
		order.add(finalCurrOrderTotal);
	}
	
	public static void remove() {
		int currIndex = UserInterface.index;
		orderLists.remove(currIndex);
		Double value = order.get(currIndex);
		orderTotalCost -= value;
		order.remove(currIndex);
		
	}
	
	public static void main(String[] args) {
		System.out.print("This is a functions class");
	}

}
