/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewBean;

/**
 *
 * @author inftel12
 */
public class LoginViewBean {
    private String user;
    private String password;

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
    
    public boolean autentication(String passwordDB,String password){
        
        if(passwordDB.equals(password)){
            return true;
        }
        else
        {
            return false;
        }
    }
}
