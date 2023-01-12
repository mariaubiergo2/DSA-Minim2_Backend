package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GameManager {
    public int numUsers();
    public int numGadgets();
    public int numFAQs();
    public String addUser(String name, String surname, String date, String mail, String password) throws EmailAlreadyBeingUsedException, SQLException;
    public void addFAQ(String q, String a) throws SQLException, FAQAlreadyBeingAskedException;
    public Map<String, User> getUsers();
    public User getUser(String idUser) throws UserDoesNotExistException;
    public String userLogin(Credentials credentials) throws IncorrectCredentialsException, SQLException;
    public List<Gadget> gadgetList();
    public List<FAQ> FAQsList();
    public void addGadget(String idGadget, int cost, String description, String unityShape) throws SQLException, GadgetWithSameIdAlreadyExists;
    public void updateGadget(Gadget gadget) throws GadgetDoesNotExistException, SQLException;
    public void updateUser(UserInformation user, String idUser) throws SQLException;
    public void buyGadget(String idGadget, String idUser) throws NotEnoughMoneyException, GadgetDoesNotExistException, UserDoesNotExistException, SQLException;
    public Object getGadget(String id) throws GadgetDoesNotExistException;
    public Object deleteGadget(String id) throws GadgetDoesNotExistException;
    public List<Gadget> purchasedGadgets(String idUser) throws SQLException, NoPurchaseWasFoundForIdUser, GadgetDoesNotExistException;
}
