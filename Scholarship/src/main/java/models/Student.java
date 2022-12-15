package models;

public class Student {
    int serialNumber;
    String firstName;
    String lastName;
    int telephoneNumber;
    String group;
    int numberOfGrades;
    int[] grades;
    boolean forerunner;
    boolean scholarshipStatus;
    int scholarshipAmount = 0;
    double averageGrade;

    public Student(){}

    public Student(int serialNumber, String lastName, String firstName, int telephoneNumber)
    {
        this.serialNumber = serialNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephoneNumber = telephoneNumber;
        grades = new int[50];
    }
    
    public String getGroup()
    {
        return this.group;
    }

    public boolean getStatus ()
    {
        return this.scholarshipStatus;
    }
    
    public boolean getForerunnerStatus ()
    {
        return this.forerunner;
    }
    
    public double getAverage ()
    {
        return this.averageGrade;
    }
    
    public void setGroup (String group)
    {
        this.group = group;
    }
    
    public void setNumberOfGrades (int number)
    {
        this.numberOfGrades = number;
    }
    
    public int getSerial()
    {
        return this.serialNumber;
    }
    
    public void gradeAdd(String[] lineOfGrades, int numberOfGrades)
    {
        for(int i = 0; i < numberOfGrades; i++)
        {
            int grade = Integer.parseInt(lineOfGrades[i]);
            this.grades[i] = grade;
        }
        
    }
    
    public void averageStudentsGrade()
    {
        int sum = 0;
        for(int i = 0; i < this.numberOfGrades; i++)
        {
            sum = sum + this.grades[i];
        }
        this.averageGrade = sum / this.numberOfGrades;
    }
    
    public void status(double averageNeededGrade)
    {
        boolean flag = true;
        for(int i = 0; i < this.numberOfGrades; i++)
        {
            if(grades[i] <= 4)
            {
                flag = false;
                break;
            }
        }
        this.scholarshipStatus = (this.averageGrade > averageNeededGrade) && (flag); 
    }
    
    public void forerunnerCreator()
    {
        int counter = 0;
        for(int i = 0; i < this.numberOfGrades; i++)
            if(this.grades[i] > 8)
                counter++;
        this.forerunner = this.scholarshipStatus && (counter == this.numberOfGrades);
    }
    
    public void scholarshipDistribution(int scholarshipFund, int numberOfScholarshipStudents, int numberOfForerunners)
    {
        if(this.scholarshipStatus)
        {
            double amount = (scholarshipFund / ((numberOfScholarshipStudents - numberOfForerunners) + numberOfForerunners * 1.1));    
            
            if(this.forerunner)
                this.scholarshipAmount = (int) (Math.floor(amount) * 1.1);
            else
                this.scholarshipAmount = (int) Math.floor(amount);
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("%5d %9s %s %7s %10d %12s %13s %10d", 
                serialNumber, firstName, lastName, 
                group, telephoneNumber, scholarshipStatus,
                forerunner, scholarshipAmount);
    }

    @Override
    public boolean equals(Object student) 
    {
        return ((Student) student).serialNumber == this.serialNumber;
    }
    
    public boolean compare(Object student) 
    {
        int p = ((Student) student).firstName.compareTo(this.firstName);
        return ((Student) student).scholarshipAmount > this.scholarshipAmount || 
                (((Student) student).scholarshipAmount == this.scholarshipAmount && p < 0); 
    }
}
