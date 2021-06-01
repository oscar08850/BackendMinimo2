package edu.upc.dsa;

import edu.upc.dsa.models.Medalla;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface GameManager {

    public void registrar(String username, String contraseña, String email);

    public void addMedalla(String username, String medalla);

    public boolean logIn (String username, String password);

    public Usuario getUser(String username);

    List<Medalla> getAllMedallas();

    List<Usuario> getSortedUsersList();

    public int borrarUsuario(String username);

    //Funciones para testear:
    public int usuariosSize();




}
