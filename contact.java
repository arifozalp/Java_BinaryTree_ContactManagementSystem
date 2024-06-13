public class contact {
    String firstName;
    String lastName;
    String ID;
    String mail;
    //This is the constructor of the contact class.
    contact(String firstName,
            String lastName,
            String ID,
            String mail) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.ID=ID;
        this.mail=mail;
    }
    //We use this function while printing this class.
    @Override
    public String toString(){
        return firstName+","+lastName+","+ID+","+mail;
    }
}
