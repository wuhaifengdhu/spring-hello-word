package sg.ntu.itcm.model;

import java.util.Calendar;
import java.util.Date;

public class EmailVerificationToken {
	private static final int EXPIRATION = 60 * 24;
	
    private Integer id;

    private String userUuid;

    private String emailVerificationToken;

    private Date expiryDate;
    
    public EmailVerificationToken() {
        super();
    }
    
    public EmailVerificationToken(final String token) {
        super();

        this.emailVerificationToken = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public EmailVerificationToken(final String userUuid, final String token) {
        super();

        this.emailVerificationToken = token;
        this.userUuid = userUuid;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken == null ? null : emailVerificationToken.trim();
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.emailVerificationToken = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(emailVerificationToken).append("]")
        .append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }
}