import java.util.Scanner;

class Employee // Base class
{
    int employeeID;
    String employeeName;
    String designation;
    double wage; // weekly wage variable for calculations
    double total_wage; // total weekly wage including bonus

    protected double calculatedBonus() // bonus calculation method
    {
        double bonus = wage * 0.10; // calculate 10% bonus
        total_wage = wage + bonus;
        return total_wage;
    }

    public double calculateAnnualSalary() // calculates annual salary
    {
        return total_wage * 52; // assuming 52 weeks in a year
    }

    public void displayEmployeeInfo() 
    {
        System.out.println("ID: " + employeeID);
        System.out.println("Name: " + employeeName);
        System.out.println("Designation: " + designation);
        System.out.println("Total Weekly Wage (Including Bonus): " + total_wage);
    }
}

class HourlyEmployee extends Employee // Subclass
{
    double hourlyRate;
    int hoursWorked;

    public double weeklyWage() // calculates weekly wage for hourly employee
    {
        wage = hourlyRate * hoursWorked;
        return wage;
    }

    @Override
    public void displayEmployeeInfo() // overriding displayEmployeeInfo
    {
        weeklyWage(); // calculate wage before displaying
        super.calculatedBonus(); // calculate bonus
        super.displayEmployeeInfo();
        System.out.println("Weekly Wage: " + wage);
    }
}

class SalariedEmployee extends Employee // Subclass
{
    double monthlySalary;

    public double weeklyWage() // calculates weekly wage for salaried employee
    {
        wage = monthlySalary / 4;
        return wage;
    }

    @Override
    public void displayEmployeeInfo() // overriding displayEmployeeInfo
    {
        weeklyWage(); 
        super.calculatedBonus(); 
        super.displayEmployeeInfo();
        System.out.println("Weekly Wage: " + wage);
    }
}

class ExecutiveEmployee extends SalariedEmployee // Executive subclass
{
    double executiveBonus;

    @Override
    protected double calculatedBonus() 
    {
        double baseBonus = super.calculatedBonus();
        executiveBonus = baseBonus + 4000; // additional executive bonus
        total_wage = wage + executiveBonus;
        return total_wage;
    }

    @Override
    public void displayEmployeeInfo() 
    {
        weeklyWage(); 
        calculatedBonus(); 
        super.displayEmployeeInfo();
        System.out.println("Executive Bonus: " + executiveBonus);
    }
}

public class Payroll_Program3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Enter employee name: ");
        String employeeName = scanner.nextLine();

        System.out.println("Enter employee designation (professor, TA, security, admin): ");
        String designation = scanner.nextLine().toLowerCase();

        Employee employee;

        if (designation.equals("security") || designation.equals("ta")) {
            HourlyEmployee hourlyEmployee = new HourlyEmployee();
            hourlyEmployee.employeeID = employeeID;
            hourlyEmployee.employeeName = employeeName;
            hourlyEmployee.designation = designation;

            System.out.println("Enter hourly rate: ");
            hourlyEmployee.hourlyRate = scanner.nextDouble();

            System.out.println("Enter hours worked: ");
            hourlyEmployee.hoursWorked = scanner.nextInt();
            
            employee = hourlyEmployee;
        } else if (designation.equals("professor") || designation.equals("admin")) {
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
        } else {
            System.out.println("Invalid designation.");
            scanner.close();
            return;
        }

        // Display employee information
        System.out.println("\nEmployee Information:");
        employee.displayEmployeeInfo();

        // Calculate and display annual salary
        double annualSalary = employee.calculateAnnualSalary();
        System.out.println("Annual Salary (Including Bonuses): " + annualSalary);

        scanner.close();
    }
}
