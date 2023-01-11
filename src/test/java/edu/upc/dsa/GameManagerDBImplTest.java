package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class GameManagerDBImplTest {
    GameManager gameManager;

    @Before
    public void setUp() {
        gameManager = new GameManagerDBImpl();
    }

    @After
    public void tearDown(){this.gameManager = null;}

    @Test
    public void testPurchasedGadgets() {
        String userId;
        List<String> gadgetIds;
        try{
            userId = this.gameManager.addUser("Test123", "Test123", "123", "Test123", "Test123");
        } catch (SQLException | EmailAlreadyBeingUsedException e){return;}
        Assert.assertThrows(NoPurchaseWasFoundForIdUser.class, ()-> this.gameManager.purchasedGadgets(userId));
        try {
            this.gameManager.buyGadget("1", userId);
            gadgetIds = this.gameManager.purchasedGadgets(userId);
        } catch (NotEnoughMoneyException | SQLException | GadgetDoesNotExistException | UserDoesNotExistException | NoPurchaseWasFoundForIdUser e) {return;}
        Assert.assertEquals(gadgetIds.get(0), "1");
    }

}
