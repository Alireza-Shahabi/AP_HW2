package members;

public class Employee extends Person {
    private static int nextId = 1;
    private String employeeId;
    private String position;
    private double salary;
    private int hoursWorked;

    public Employee(String name, String phoneNumber, String position, double salary) {
        super(name, phoneNumber);
        this.employeeId = String.format("E%03d", nextId++);
        this.position = position;
        this.salary = salary;
        this.hoursWorked = 0;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void addHoursWorked(int hours) {
        this.hoursWorked += hours;
    }


    public double calculateSalary() {
        int baseHours = 160;
        if (hoursWorked <= baseHours) {
            return salary;
        } else {
            int extraHours = hoursWorked - baseHours;
            double extraPay = (extraHours / (double) baseHours) * (salary * 1.5);
            return salary + extraPay;
        }
    }

    @Override
    public String getInfo() {
        return "ID: " + employeeId
                + ", Name: " + getName()
                + ", Phone: " + getPhoneNumber()
                + ", Position: " + position
                + ", HoursWorked: " + hoursWorked;
    }
}

