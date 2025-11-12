package com.app.client;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.app.config.HibernateUtil;
import com.app.model.Bank;

public class Test {
	
	// Creating a single Bank object to store account details temporarily
	Bank b = new Bank();
	
	// Getting the SessionFactory object from Hibernate utility class
	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	// Opening a new Hibernate session
	Session s = sf.openSession();
	
	// Scanner object for taking input from user
	Scanner sc = new Scanner(System.in);
	
	
	// Method to create a new bank account and store it in the database
	public void createAccount() {
		
		System.out.println("Enter your name:");
		b.setCustomername(sc.next());
		
		System.out.println("Enter account number:");
		b.setAccountnumber(sc.nextInt());
		
		System.out.println("Enter initial balance:");
		b.setBalance(sc.nextDouble());
		
		System.out.println("Account created successfully!");
		
		// Save the Bank object in database
		s.persist(b);
		
		// Commit the transaction to save changes permanently
		s.beginTransaction().commit();
	}
	
	
	// Method to deposit money into an existing account
	public void deposite() {
		System.out.println("Enter account number:");
		int acc = sc.nextInt();
		
		// Check if entered account number matches the created account
		if (b.getAccountnumber() == acc) {
			System.err.println("Enter amount you want to deposit:");
			int amount = sc.nextInt();
			
			// Validate positive amount
			if (amount > 0) {
				double balance = b.getBalance();
				balance = balance + amount;  // Add deposited amount
				b.setBalance(balance);
				
				System.out.println("Your current balance is: ₹" + b.getBalance());
				
				// Update the record in database
				s.update(b);
				s.beginTransaction().commit();
				
			} else {
				System.out.println("Invalid deposit amount!");
				return;
			}
		} else {
			System.err.println("Account not found!");
		}
	}
	
	
	// Method to withdraw money from an existing account
	public void withdraw() {
		System.out.println("Enter account number to withdraw:");
		int number = sc.nextInt();
		
		// Check if account number matches
		if (b.getAccountnumber() == number) {
			System.out.println("Enter amount you want to withdraw:");
			int amt = sc.nextInt();
			
			// Check for positive withdrawal amount
			if (amt > 0) {
				double balance = b.getBalance();
				
				// Deduct withdrawal amount
				balance = balance - amt;
				b.setBalance(balance);
				
				System.out.println("Your remaining balance is: ₹" + balance);
				
				// Update record in database
				s.update(b);
				s.beginTransaction().commit();
				
				// Closing session (optional here, as it’s used in the same class)
				s.close();
			} else {
				System.out.println("Invalid withdrawal amount!");
				return;
			}
		} else {
			System.err.println("Account not found!");
		}
	}
	
	
	// Method to display all saved account details
	public void showDetails() {
		// Loop to check account records by ID (assuming IDs start from 1)
		for (int i = 1; i <= 10; i++) {
			
			// Using get() method to retrieve record by primary key (id)
			Bank b = s.get(Bank.class, i);
			
			// If record exists, print account details
			if (b != null) {
			    System.out.println("===== Account Details =====");
			    System.out.println("Customer Name: " + b.getCustomername());
			    System.out.println("Account Number: " + b.getAccountnumber());
			    System.out.println("Current Balance: ₹" + b.getBalance());
			    System.out.println("===========================");
			}
			else {
				System.err.println("Data not found for ID: " + i);
			}
		}
	}
	
	
	// Method to display main menu and handle user input
	public void menu() {
		while (true) {
			System.out.println("****** MENU ******");
			System.out.println("1. Create Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Show Details");
			System.out.println("5. Exit");
			System.out.println("******************");
			
			System.out.println("Enter your choice:");
			int ch = sc.nextInt();
			
			// Menu-driven control flow
			switch (ch) {
				case 1: {
					createAccount();
					break;
				}
				case 2: {
					deposite();
					break;
				}
				case 3: {
					withdraw();
					break;
				}
				case 4: {
					showDetails();
					break;
				}
				case 5: {
					System.out.println("Thank you for using the banking system!");
					System.exit(0);
					break;
				}
				default:
					System.out.println("Invalid choice! Please try again.");
			}
		}
	}
	
	
	// Main method — program execution starts here
	public static void main(String[] args) {
		Test t = new Test();
		t.menu();  // Display menu options to the user
	}
}
