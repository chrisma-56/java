import java.util.Scanner;

class Employee // Base class
{
    int employeeID;
    String employeeName;
    String designation;
    double wage; 
    double total_wage; 
    protected double calculatedBonus() // bonus calculation method
    {
        double bonus = wage * 0.10; // calculate 10% bonus
        total_wage = wage + bonus;
        return total_wage;
    }

    public void displayEmployeeInfo() 
    {
        System.out.println("ID: " + employeeID);
        System.out.println("Name: " + employeeName);
        System.out.println("Designation: " + designation);
        System.out.println("Total Wage (Including Bonus): " + total_wage);
    }
}

class HourlyEmployee extends Employee // Subclass
{
    double hourlyRate;
    int hoursWorked;

    public double weeklyWage() // weekly wage for hourly employee
    {
        wage = hourlyRate * hoursWorked;
        return wage;
    }

    public void displayEmployeeInfo() // overriding
    {
        weeklyWage(); 
        super.calculatedBonus(); // calculate bonus
        super.displayEmployeeInfo();
        System.out.println("Weekly Wage: " + wage);
    }
}

class SalariedEmployee extends Employee // Subclass
{
    double monthlySalary;

    public double weeklyWage() //  weekly wage for salaried employee
    {
        wage = monthlySalary / 4;
        return wage;
    }

   
    public void displayEmployeeInfo() // overriding 
    {
        weeklyWage(); 
        super.calculatedBonus(); // using super keyword
        super.displayEmployeeInfo();
        System.out.println("Weekly Wage: " + wage);
    }
}

class ExecutiveEmployee extends SalariedEmployee // Executive subclass
{
    double executiveBonus;
    protected double calculatedBonus() 
    {
        double bonusPercentage = super.calculatedBonus();
        executiveBonus = bonusPercentage + 4000; // additional executive bonus
        return executiveBonus;
    }

   
    public void displayEmployeeInfo() 
    {
        weeklyWage(); // calculate wage before displaying
        calculatedBonus(); // calculate executive bonus
        super.displayEmployeeInfo();
        System.out.println("Executive Bonus: " + executiveBonus);
    }
}

public class Program3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in); //scanner odject to get the user information
        
        System.out.println("Enter employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter employee name: ");
        String employeeName = scanner.nextLine();

        System.out.println("Enter employee designation (professor, TA, security, admin): ");
        String designation = scanner.nextLine().toLowerCase();

        Employee employee;

        if (designation.equals("security") || designation.equals("ta")) // if position entered as ta or security consider as hourly employee
         { 
            HourlyEmployee hourlyEmployee = new HourlyEmployee();
            hourlyEmployee.employeeID = employeeID;
            hourlyEmployee.employeeName = employeeName;
            hourlyEmployee.designation = designation;

            System.out.println("Enter hourly rate: ");
            hourlyEmployee.hourlyRate = scanner.nextDouble();

            System.out.println("Enter hours worked: ");
            hourlyEmployee.hoursWorked = scanner.nextInt();
            
            employee = hourlyEmployee;
        } 
        else if (designation.equals("professor") || designation.equals("admin")) // if proffessor or admin then salaried employee
        { 
            SalariedEmployee salariedEmployee;
            if (designation.equals("admin")) {
                salariedEmployee = new ExecutiveEmployee();
            } else {
                salariedEmployee = new SalariedEmployee();
            }
            salariedEmployee.employeeID = employeeID;
            salariedEmployee.employeeName = employeeName;
            salariedEmployee.designation = designation;

            System.out.println("Enter monthly salary: ");
            salariedEmployee.monthlySalary = scanner.nextDouble();

            employee = salariedEmployee;
        } 
        else 
        {
            System.out.println("Invalid designation.");
            return;
        }

        System.out.println("Employee Information:");
        employee.displayEmployeeInfo();

        scanner.close();
    }
}

