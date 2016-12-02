package sg.ntu.itcm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInfoDto {
	@NotNull
    private Integer primaryKey;
	
	@NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;
    
    @NotNull
    private Integer age;

    @NotNull
    @Size(min = 1)
    private String gender;
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getActivationStatus() {
        return "YES";
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstName).append("]")
        .append("[lastName=").append(lastName).append("]")
        .append("[age=").append(age).append("]")
        .append("[gender=").append(gender).append("]");
        return builder.toString();
    }

}
