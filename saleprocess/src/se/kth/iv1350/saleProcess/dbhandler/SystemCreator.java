package se.kth.iv1350.saleProcess.dbhandler;


/**
 * This class handles all the creations of the systems included in the application.
 */
public class SystemCreator {
    private ExternalAccounting externalAccounting;
    private CashRegister cashRegister;
    private InventorySystem inventorySystem;
    private Printer printer;

    /**
     * Creates an instance.
     */
    public SystemCreator(){

        this.externalAccounting = new ExternalAccounting();
        this.cashRegister = new CashRegister();
        this.inventorySystem = new InventorySystem();
        this.printer = new Printer();
    }

    /**
     * Returns the instantiated inventorySystem.
     *
     * @return An object of type <code>InventorySystem<code/>
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    /**
     * Returns the instantiated cashRegister.
     *
     * @return An object of type <code>CashRegister<code/>
     */
    public CashRegister getCashRegister(){
        return this.cashRegister;
    }

    /**
     * Returns the instantiated externalAccounting..
     *
     * @return An object of type <code>ExternalAccounting<code/>.
     */
    public ExternalAccounting getExternalAccounting(){
        return this.externalAccounting;
    }

    /**
     * Returns the instantiated printer.
     *
     * @return An object of type <code>Printer<code/>.
     */
    public Printer getPrinter(){
        return this.printer;
    }
}
