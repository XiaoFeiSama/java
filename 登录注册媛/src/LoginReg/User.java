package LoginReg;

public class User {
    private int uno;
    private String user;
    private String password;

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uno=" + uno +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
