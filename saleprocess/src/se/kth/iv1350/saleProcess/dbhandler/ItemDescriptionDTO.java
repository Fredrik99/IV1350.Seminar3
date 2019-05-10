package se.kth.iv1350.saleProcess.dbhandler;

/**
 * This class contains the descriptions of each
 * individual item in the inventory system
 */

 final class ItemDescriptionDTO {
    private String description;

    /**
     * Creates an instance.
     *
     * @param description The description of the <code>ItemDTO<code/>.
     */
     ItemDescriptionDTO(String description){
        this.description = description;
    }

    /**
     * Returns the description of an <code>ItemDTO<code/>.
     *
     * @return The description in form of a <code>String<code/>
     */
     String getDescription(){
       return description;
    }
}
