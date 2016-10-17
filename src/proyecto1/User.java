/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author dam2
 */
public class User implements java.io.Serializable{
    
    private String name;
    private String surname;
    private String dni;
    private String permissions;
    private String email;
    private String username;
    private String password;
    
    User(){
    
    }
    
    User(String name, String surname, String dni, String permissions, String email, String username, String password){
    
        this.name           = name;
        this.dni            = dni;
        this.permissions    = permissions;
        this.surname        = surname;
        this.email          = email;
        this.username       = username;
        this.password       = password;
    }
    
    User (User user){
        this.name           = user.getName();
        this.dni            = user.getDni();
        this.permissions    = user.getPermissions();
        this.surname        = user.getSurname();
        this.email          = user.getEmail();
        this.username       = user.getUsername();
        this.password       = user.getPassword();
    
    }
    
    //SET METHODS

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    //GET METHODS

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDni() {
        return dni;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
