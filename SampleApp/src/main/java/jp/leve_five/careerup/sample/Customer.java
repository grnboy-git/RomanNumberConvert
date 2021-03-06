package jp.leve_five.careerup.sample;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector _rentals = new Vector();
	
	public Customer(String name){
		_name = name;
	}
	
	public void addRental(Rental arg){
		_rentals.addElement(arg);
	}
	
	public String getName(){
		return _name;
	}
	
	public String statement(){
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "/n";
		
		while(rentals.hasMoreElements()){
			double thisAmount = 0;
			Rental each = (Rental)rentals.nextElement();
			
			//一行ごとに金額を計算
			thisAmount = amauntFor(each);
			
			//レンタルポイントを加算
			frequentRenterPoints ++;
			//新作を２日以上借りた場合はボーナスポイント
			if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
					each.getDaysRented() > 1)frequentRenterPoints ++;
			
			//この貸出に関する数値の表示
			result += "/t" + each.getMovie().getTitle() + "/t" +
					String.valueOf(thisAmount) + "n/";
			totalAmount += thisAmount;
		}
		//フッタ部分の追加
		result += "Amount owed is " + String.valueOf(totalAmount) + "/n";
		result += "You earned " + String.valueOf(frequentRenterPoints) +
					" frequent renter points";
		return result;
	}

	private double amauntFor(Rental aRental) {
		double result = 0;
		switch(aRental.getMovie().getPriceCode()){
		case Movie.REGULAR:
			result += 2;
			if(aRental.getDaysRented() > 2)
				result += (aRental.getDaysRented() - 2 * 1.5);
			break;
		case Movie.NEW_RELEASE:
			result += aRental.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			result += 1.5;
			if(aRental.getDaysRented() > 3)
				result += (aRental.getDaysRented() - 3) * 1.5;
			break;

		}
		return result;
	}

}
