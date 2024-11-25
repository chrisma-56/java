interface BankInterface
{
    double getBanlance(); //declaring for balance and interface
    double getInterestRate(); //for interest
}
class Bank_a implements BankInterface
{
   double deposit = 10000;
   public double getBanlance() //
   {
    double interest = deposit * getInterestRate();
        return deposit + interest;
   }
   public double getInterestRate() 
   {
    return 0.07; 
}

}
class Bank_b implements BankInterface
{
    double deposit = 150000;
    
   public double getBanlance() //
   {
    double interest = deposit * getInterestRate();
        return deposit + interest;
   }
   public double getInterestRate() 
   {
    return 0.074; 
}

}
class Bank_c implements BankInterface
{
    double deposit = 200000;
    
   public double getBanlance() //
   {
    double interest = deposit * getInterestRate();
        return deposit + interest;
   }
   public double getInterestRate() 
   {
    return 0.079; 
}

}

class program5_1
{
    public static void main(String[] args)
    {
        Bank_a obj1 = new Bank_a();
        Bank_b obj2 = new Bank_b();
        Bank_c obj3 = new Bank_c();

        System.out.println("the total saving in bank A : "+obj1.getBanlance());
        System.out.println("the total  saving in bank B : "+obj2.getBanlance());
        System.out.println("the total  saving in bank C : "+obj3.getBanlance());

    }
}