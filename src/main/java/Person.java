import java.util.Date;

class Person{
    private String firstName;
    private String secondName;
    private String lastName;
    private Date birthday;
    private String address;

    Person(){
        this.firstName = "null";
        this.secondName = "null";
        this.lastName = "null";
        this.birthday = new Date(2000, 1, 3);
        this.address = "null";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    private Date toDate(String date) throws DataConverException {
        String[] words = date.split("\\.");
        int day = Integer.parseInt(words[0]);
        int month = Integer.parseInt(words[1])-1;
        int year = Integer.parseInt(words[2]);

        if (day < 1 || day > 31) throw new DataConverException("Неверный день!");
        if (month < 0 || month > 11) throw new DataConverException("Неверный месяц!");
        return new Date(year, month, day);
    }

    public void setBirthday(String date) throws DataConverException {
        this.birthday = toDate(date);

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName(){
        return secondName + " " + firstName + " " + " " + lastName;
    }

    public int getBornYear(){
        return birthday.getYear();
    }

    public String getAddress(){
        return this.address;
    }
}