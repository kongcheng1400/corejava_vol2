package ch1.textFile;

import java.io.*;
import java.util.*;

import generalClass.Employee;

public class TextFileTest
{
	public static void main(String[] args) throws IOException
	{
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Carl Cracker", 76000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 76000, 19889, 11, 11);
		staff[2] = new Employee("Tony Tester", 40000, 1990, 12, 13);
		
		try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8"))
		{
			writeData(staff,out);
		}
		
		try (Scanner in = new Scanner(
				new FileInputStream("employee.dat"), "UTF-8"))
		{
			Employee[] newStaff = readData(in);
			
			//print the newly read employee records
			for (Employee e : newStaff)
				System.out.println(e);
		}
	}
	
	private static void writeData(Employee[] employees, PrintWriter out) throws IOException
	{
		out.println(employees.length);
		
		for (Employee e : employees)
			writeEmployee(out, e);
	}
	
	private static Employee[] readData(Scanner in)
	{
		int n = in.nextInt();
		in.nextLine();
		
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i++)
		{
			employees[i] = readEmployee(in);
		}
		return employees;
	}
	
	public static void writeEmployee(PrintWriter out, Employee e)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(e.getHireDay());
		out.println(e.getName() + "|" + e.getSalary() + "|" + calendar.get(Calendar.YEAR) +
				"|" + calendar.get(Calendar.MONTH) + "|" + calendar.get(Calendar.MONTH));
	}
	
	public static Employee readEmployee(Scanner in)
	{
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		String name = tokens[0];
		double salary = Double.parseDouble(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		int month = Integer.parseInt(tokens[3]);
		int day = Integer.parseInt(tokens[4]);
		return new Employee(name, salary, year, month, day);
	}
	
}