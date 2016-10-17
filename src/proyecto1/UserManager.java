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

    private Map<String, User> map;  //Map que contendrá los usuarios mientras el programa esté en ejecución
    private Gson obj;               //Objeto Gson para tratar los datos input/output a JSON
    private FileWriter fileW; //Objeto FileWriter que escribirá datos al archivo Users.json
    private FileReader fileR;       //Objeto FileReader que leerá datos del archivo Users.json
    private User user;              //Objeto User "plantilla" que rellenaremos e introduciremos a map
    private Scanner in;             //

    
    /**
     * Constructor de UserManager. Inicializa map y obj, inicializa fileR y fileW,
     * en caso e que el archivo Users.json no exista, lo crea. Si existe, llama a
     * openAndRead.
     * @throws IOException 
     */
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
     * Devuelve true si existe un usuario con el username especificado en "map"
     * @param username Usuario
     * @return
     */
    public Boolean buscarUsuario(String username) {

        if (map.containsKey(username)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve el usuario por cuyo username preguntamos.
     * @param username Usuario
     * @return 
     */
    public User userData(String username) {

        User user = map.get(username);

        return user;
    }

    /**
     * Comprueba si el usuario que intentamos introducir existe, y en caso negativo,
     * lo introduce en map.
     * @param name Nombre
     * @param surname Apellido
     * @param dni Dni
     * @param permissions Permisos
     * @param email Email
     * @param username Usuario
     * @param password Contraseña
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
     * Comprueba que el logIn sea correcto, comparando el usuario y contraseña, con los almacenadas
     * para el usuario especificado
     * @param username Usuario
     * @param password Contraseña
     * @return True si coincide.
     */
    public Boolean comprobarLogin(String username, String password) {

        String a = map.get(username).getPassword();

        if (a.equals(password)) {
            System.out.println("Paso1");
            return true;
        } else {
            System.out.println("Paso2");
            return false;
        }
    }

    /**
     * Hace las comprobaciones necesarias para modificar un usuario en función 
     * del campo que se quiera modificar, véase que el nuevo Username no exista.
     * @param username Usuario
     * @param campo Campo a modificar
     * @param nuevo Nuevo valor del campo
     * @return 
     */
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
    
    /**
     * Borra un usuario de map, en caso de que exista.
     * @param userName Usuario
     * @return true si borra el usuario.
     */
    public boolean deleteUser(String userName){
    
        if (buscarUsuario(userName)) {
            map.remove(userName);
            return true;
        }else return false;
    
    }

    /**
     * Escribe el contenido de map en el archivo Users.json y cierra el archivo.
     * @throws IOException 
     */
    public void closeAndWrite() throws IOException {

        obj.toJson(map, fileW);
        fileW.close();

    }

    /**
     * Carga la información del archivo Users.json en un String y lo pasa a map
     * en formato String, User.
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
