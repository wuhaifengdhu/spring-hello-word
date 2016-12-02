package sg.ntu.itcm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import sg.ntu.itcm.validation.PasswordMatches;
import sg.ntu.itcm.validation.ValidEmail;
import sg.ntu.itcm.validation.ValidPassword;

@PasswordMatches
public class UserDto {
    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @ValidPassword
    @NotNull
    @Size(min = 1)
    private String password;

    @ValidPassword
    @NotNull
    @Size(min = 1)
    private String matchingPassword;
    
    @NotNull
    private Integer age;

    @NotNull
    @Size(min = 1)
    private String gender;
    
    @NotNull
    @Size(min = 1)
    private String signInProvider;

    @ValidEmail
    @NotNull
    @Size(min = 1)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
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
    
    public String getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstName).append("]")
        .append("[lastName=").append(lastName).append("]")
        .append("[email=").append(email).append("]")
        .append("[password=").append(password).append("]")
        .append("[signInProvider=").append(signInProvider).append("]");
        return builder.toString();
    }
}
