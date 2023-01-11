package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Credentials;

import java.util.*;

import edu.upc.dsa.models.Gadget;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    Map<String, User> users;

    ArrayList<Gadget> gadgetList;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public GameManagerImpl() {
        this.users=new HashMap<>();
        this.gadgetList=new ArrayList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public int numUsers(){
        return users.size();
    }

    public int numGadgets(){
        return gadgetList.size();
    }

    @Override
    public String addUser(String name, String surname, String birthday, String email, String password) throws EmailAlreadyBeingUsedException{
        logger.info("Adding a new User Starting...");
        User newUser= new User(name,surname,birthday,email,password);
        List<User> userList = new ArrayList<>(this.users.values());
        logger.info("Checking whether this users exists...");

        for(User u : userList){
            if(Objects.equals(u.getEmail(), email)){
                logger.info("The user already exists!");
                throw new EmailAlreadyBeingUsedException();
            }
        }
        this.users.put(newUser.getIdUser(), newUser);
        logger.info("User " + newUser.getName() +" has been added correctly with the id " + newUser.getIdUser());
        return newUser.getIdUser();
    }

    public Map<String,User> getUsers(){
        return this.users;
    }

    @Override
    public User getUser(String idUser) throws UserDoesNotExistException {
        User u = users.get(idUser);
        if (u==null) {
            logger.warn("Identifier not found");
            throw new UserDoesNotExistException();
        }
        return u;
    }

    @Override
    public String userLogin(Credentials credentials) throws IncorrectCredentialsException {
        if (!equalCredentials(credentials)) {
            logger.warn("Credentials " + credentials.getEmail() + " and "+credentials.getPassword()+  " not found");
            throw new IncorrectCredentialsException();
        }
        else return getUserByEmail(credentials.getEmail()).getIdUser();
    }

    public Boolean equalCredentials(Credentials credentials){
        for(User u:users.values()){
            if (Objects.equals(u.getEmail(), credentials.getEmail())&&Objects.equals(u.getPassword(), credentials.getPassword())){
                return true;
            }
        }
        return false;
    }
    public List<Gadget> gadgetList(){

        List<Gadget> lista=this.gadgetList;
        lista.sort(new Comparator<Gadget>() {
            @Override
            public int compare(Gadget o1, Gadget o2) {
                return Double.compare(o1.getCost(), o2.getCost());
            }
        });
        return lista;
    }

    @Override
    public void addGadget(String idGadget, int cost, String description, String unity_Shape) {
        Gadget g= new Gadget(idGadget,cost,description,unity_Shape);
        gadgetList.add(g);
    }

    @Override
    public void updateGadget(Gadget gadget) throws GadgetDoesNotExistException {
        logger.info("updateGadget("+gadget+")");
        int position = searchGadgetPosition(gadget.getIdGadget());
        if (position==-1){
            logger.warn("This gadget not found: " + gadget.getIdGadget());
            throw new GadgetDoesNotExistException();
        }
        else{
            gadgetList.get(position).setCost(gadget.getCost());
            gadgetList.get(position).setIdGadget(gadget.getIdGadget());
            gadgetList.get(position).setDescription(gadget.getDescription());
            gadgetList.get(position).setUnityShape(gadget.getUnityShape());
            logger.info("Gadget updated");
        }
    }
    public int searchGadgetPosition(String idGadget){
        logger.info("searchGadgetPosition("+idGadget+")");
        int i=0;
        for (Gadget g: this.gadgetList) {
            if (g.getIdGadget().equals(idGadget)) {
                return i;
            }
            i+=1;
        }
        return -1;
    }
    @Override
    public void buyGadget(String idGadget, String idUser) throws NotEnoughMoneyException, GadgetDoesNotExistException, UserDoesNotExistException {
        logger.info("buyGadget("+idGadget+", "+idUser+")");
        int position = searchGadgetPosition(idGadget);
        if (position==-1){
            logger.warn("Gadget does not exist");
            throw new GadgetDoesNotExistException();
        }
        else{
            User u = users.get(idUser);
            if (u==null) {
                logger.warn("Identifier not found");
                throw new UserDoesNotExistException();
            }
            int money = users.get(idUser).getCoins();
            int cost = gadgetList.get(position).getCost();
            if (money < cost){
                logger.warn(cost+" is not enough money");
                throw new NotEnoughMoneyException();
            }
            else {
                logger.info("Gadget bought");
            }
        }
    }

    public User getUserByEmail(String email) {
        return this.users.values().stream()
                .filter(x -> (Objects.equals(x.getEmail(), email)))
                .findFirst()
                .orElse(null);
    }

    public Gadget getGadget(String idGadget) throws GadgetDoesNotExistException {
        logger.info("getGadget("+idGadget+")");
        /*for (Gadget t: this.gadgetList) {
            if (t.getId().equals(idGadget)) {
                logger.info("getGadget("+idGadget+"): "+t);
                return t;
            }
        }*/
        int position = searchGadgetPosition(idGadget);
        if (position==-1){
            logger.warn("not found " + idGadget);
            throw new GadgetDoesNotExistException();
        }
        else{
            logger.info("Gadget found"+idGadget+")");
            return gadgetList.get(position);
        }
    }

    public Gadget deleteGadget(String id) throws GadgetDoesNotExistException {
        Gadget t = this.getGadget(id);
        if (t==null) {
            logger.warn("not found " + t);
            throw new GadgetDoesNotExistException();
        }
        else logger.info(t+" deleted ");

        this.gadgetList.remove(t);
        return t;
    }

    @Override
    public List<Gadget> purchasedGadgets(String idUser) {
        return null;
    }
}