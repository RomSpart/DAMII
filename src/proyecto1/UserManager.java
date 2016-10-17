/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
public class UserManager {

    private Map<String, User> map;
    private Gson obj;
    private FileWriter fileW;
    private FileReader fileR;
    private User user;
    private Scanner in;
    private String a;

    UserManager() throws IOException {

        map = new HashMap<>();
        obj = new Gson();
        try {
            fileR = new FileReader("..\\Proyecto1\\src\\proyecto1\\Users.json");
        } catch (FileNotFoundException e1) {
            File file = new File("..\\Proyecto1\\src\\proyecto1\\Users.json");
            file.createNewFile();
            fileR = new FileReader("..\\Proyecto1\\src\\proyecto1\\Users.json");
        }

        in = new Scanner(fileR);
        if (in.hasNextLine()) {
            openAndRead();
        }
        try {
            fileW = new FileWriter("..\\Proyecto1\\src\\proyecto1\\Users.json");
        } catch (FileNotFoundException e2) {
            File file = new File("..\\Proyecto1\\src\\proyecto1\\Users.json");
            fileW = new FileWriter("..\\Proyecto1\\src\\proyecto1\\Users.json");
        }

    }

    /**
     *
     * @param username
     * @return
     */
    public Boolean buscarUsuario(String username) {

        if (map.containsKey(username)) {
            return true;
        } else {
            return false;
        }
    }

    public User userData(String username) {

        User user = map.get(username);

        return user;
    }

    /**
     *
     * @param name
     * @param surname
     * @param dni
     * @param permissions
     * @param email
     * @param username
     * @param password
     * @throws java.io.IOException
     */
    public boolean crearNuevoUsuario(String name, String surname, String dni,
            String permissions, String email, String username, String password) throws IOException {

        if (!buscarUsuario(username)) {
            user = new User(name, surname, dni, permissions, email, username, password);
            map.put(username, user);
            return true;
        }

        return false;

    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Boolean comprobarLogin(String username, String password) {

        a = map.get(username).getPassword();

        if (a.equals(password)) {
            System.out.println("Paso1");
            return true;
        } else {
            System.out.println("Paso2");
            return false;
        }
    }

    public boolean modificarUsuario(String username, String campo, String nuevo) {

        boolean token = true;
        switch (campo) {
            case "username":
                if (!buscarUsuario(nuevo)) {
                    map.get(username).setUsername(nuevo);
                    map.put(nuevo, map.get(username));
                    map.remove(username);
                    break;
                } else {
                    token = false;
                    break;
                }
            case "password":
                map.get(username).setPassword(nuevo);

                break;
            case "name":
                map.get(username).setName(nuevo);

                break;
            case "surname":
                map.get(username).setSurname(nuevo);

                break;
            case "email":
                map.get(username).setEmail(nuevo);

                break;
            case "permission":
                map.get(username).setPermissions(nuevo);

                break;
            case "dni":
                map.get(username).setDni(nuevo);

                break;
        }

        return token;
    }
    
    public boolean deleteUser(String userName){
    
        if (buscarUsuario(userName)) {
            map.remove(userName);
            return true;
        }else return false;
    
    }

    public void closeAndWrite() throws IOException {

        obj.toJson(map, fileW);
        fileW.close();

    }

    private void openAndRead() throws FileNotFoundException, IOException {

        String inString = this.in.nextLine();
        Type type = new TypeToken<Map<String, User>>() {
        }.getType();
        map = obj.fromJson(inString, type);
//        map = obj.fromJson(inString, map.getClass());

        //{"r":{"name":"r","surname":"r","dni":"r","permissions":"777","email":"r","username":"r","password":"r"}}
        //user = new User(name, surname, dni, permissions, email, username, password);
        //map.put(username, user);
    }

}
