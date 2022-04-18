package data;

public class AuthUser {
    private String token;

    public AuthUser(String token) {
        this.token = token;
    }

    public AuthUser(){
        super();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
