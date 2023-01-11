package edu.upc.dsa;


import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.Gadget;
import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

public class GameManagerImplTest {
    /*
    GameManager gameManager;
    String idAlba;
    String idPaula;
    String idMaria;
    String idGenis;
    String idGuillem;

    @Before
    public void setUp() throws EmailAlreadyBeingUsedException, SQLException {
        gameManager = new GameManagerImpl();
        this.idAlba= gameManager.addUser("Alba","Roma", "23112001","albaroma@gmail.com","Test123");
        this.idPaula= gameManager.addUser( "Paula","Sopena", "22052001","paulasopena@gmail.com","Test123");
        this.idMaria= gameManager.addUser("Maria", "Ubiergo", "02112001","mariaubiergo@gmail.com","Test123");
        this.idGenis= gameManager.addUser("Genis","Castillo", "23112001","geniscastillo@gmail.com","Test123");
        this.idGuillem= gameManager.addUser( "Guillem","Purti", "22052001","guillempurti@gmail.com","Test123");

        gameManager.addGadget("1A", 10, "Fire", "Fire flames");
        gameManager.addGadget("2A", 4, "Water", "Blue water");
        gameManager.addGadget("3A", 55, "Air", "Fresh air");

    }

    @After
    public void tearDown(){this.gameManager = null;}

    @Test
    public void testAddUserAlreadyExistingThrowsException(){
        Assert.assertEquals(5, this.gameManager.numUsers());
        Assert.assertThrows(EmailAlreadyBeingUsedException.class, ()->this.gameManager.addUser("test", "test", "test", "albaroma@gmail.com", "test"));
        Assert.assertEquals(5, this.gameManager.numUsers());
    }

    @Test
    public void testAddUserIsPossible() throws EmailAlreadyBeingUsedException, SQLException {
        Assert.assertEquals(5, this.gameManager.numUsers());
        this.gameManager.addUser("test", "test", "test", "susana@gmail.com", "test");
        Assert.assertEquals(6, this.gameManager.numUsers());
    }

    @Test
    public void testAddGadgetIsPossible(){
        Assert.assertEquals(3,this.gameManager.numGadgets());
        this.gameManager.addGadget("4A",3,"Earth","Green Earth");
        Assert.assertEquals(4,this.gameManager.numGadgets());
    }

    @Test
    public void testUpdateGadgetIsPossible() throws GadgetDoesNotExistException {
        Assert.assertEquals("1A",this.gameManager.getGadget("1A").getIdGadget());
        Assert.assertEquals(10,this.gameManager.getGadget("1A").getCost());
        Assert.assertEquals("Fire",this.gameManager.getGadget("1A").getDescription());
        Assert.assertEquals("Fire flames",this.gameManager.getGadget("1A").getUnityShape());
        Gadget g= new Gadget("1A",12,"Earth","Green Earth");
        this.gameManager.updateGadget(g);
        Assert.assertEquals("1A",this.gameManager.getGadget("1A").getIdGadget());
        Assert.assertEquals(12,this.gameManager.getGadget("1A").getCost());
        Assert.assertEquals("Earth",this.gameManager.getGadget("1A").getDescription());
        Assert.assertEquals("Green Earth",this.gameManager.getGadget("1A").getUnityShape());
    }
    @Test
    public void testUpdateGadgetDoesNotExist() {
        Assert.assertEquals(3,this.gameManager.numGadgets());
        Gadget g= new Gadget("4A",3,"Earth","Green Earth");
        Assert.assertThrows(GadgetDoesNotExistException.class, ()->this.gameManager.updateGadget(g));
        Assert.assertEquals(3,this.gameManager.numGadgets());
    }

    @Test
    public void testBuyGadgetIncorrectId(){
        Assert.assertEquals(idGuillem,this.gameManager.getUsers().get(idGuillem).getIdUser());
        Assert.assertThrows(IncorrectIdException.class, ()->this.gameManager.buyGadget("3A","ggff"));
    }

    @Test
    public void testGetUsersIsPossible(){
        Map<String,User> userList = this.gameManager.getUsers();
        Assert.assertEquals(5,userList.size());
        Assert.assertEquals("Alba",userList.get(idAlba).getName());
        Assert.assertEquals("Sopena",userList.get(idPaula).getSurname());
        Assert.assertEquals("mariaubiergo@gmail.com",userList.get(idMaria).getEmail());
        Assert.assertEquals("23112001",userList.get(idGenis).getBirthday());
        Assert.assertEquals("Test123",userList.get(idGuillem).getPassword());
    }
    @Test
    public void testLoginIsPossible() throws IncorrectCredentialsException, SQLException {
        Map<String,User> userList = this.gameManager.getUsers();
        Assert.assertEquals("guillempurti@gmail.com",userList.get(idGuillem).getEmail());
        Assert.assertEquals("Test123",userList.get(idGuillem).getPassword());
        Credentials credentials = new Credentials("guillempurti@gmail.com","Test123");
        this.gameManager.userLogin(credentials);
    }
    @Test
    public void testLoginIncorrectCredentials(){
        Map<String,User> userList = this.gameManager.getUsers();
        Assert.assertEquals("guillempurti@gmail.com",userList.get(idGuillem).getEmail());
        Assert.assertEquals("Test123",userList.get(idGuillem).getPassword());
        Credentials credentials = new Credentials("purti@gmail.com","Test123");
        Assert.assertThrows(IncorrectCredentialsException.class, ()-> this.gameManager.userLogin(credentials));
    }
    @Test
    public void testGetGadgetListIsPossible(){
        List<Gadget> gadgetList = this.gameManager.gadgetList();
        Assert.assertEquals(3,gadgetList.size());
        Assert.assertEquals("2A",gadgetList.get(0).getIdGadget());
        Assert.assertEquals("1A",gadgetList.get(1).getIdGadget());
        Assert.assertEquals("3A",gadgetList.get(2).getIdGadget());
    }
    @Test
    public void testDeleteGadgetIsPossible() throws GadgetDoesNotExistException{
        List<Gadget> gadgetList = this.gameManager.gadgetList();
        Object eliminado = this.gameManager.deleteGadget("2A");
        Assert.assertEquals(2,gadgetList.size());
        Assert.assertEquals("2A",eliminado.getIdGadget());
        Assert.assertEquals("1A",gadgetList.get(0).getIdGadget());

    }
    @Test
    public void testDeleteGadgetDoesNotExist(){
        Assert.assertThrows(GadgetDoesNotExistException.class, ()-> this.gameManager.deleteGadget("2B"));
    }
    @Test
    public void testGetGadgetIsPossible() throws GadgetDoesNotExistException{
        List<Gadget> gadgetList = this.gameManager.gadgetList();
        Gadget recogido= (Gadget) this.gameManager.getGadget("2A");
        Assert.assertEquals("2A",gadgetList.get(0).getIdGadget());
        Assert.assertEquals("2A",recogido.getIdGadget());

    }
    @Test
    public void testGetGadgetDoesNotExist(){
        Assert.assertThrows(GadgetDoesNotExistException.class, ()-> this.gameManager.getGadget("2B"));
    }

     */
}
