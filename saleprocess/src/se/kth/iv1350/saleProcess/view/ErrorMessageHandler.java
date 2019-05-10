package se.kth.iv1350.saleProcess.view;

/**
 * This class handles the error messages presented to the user.
 */
public class ErrorMessageHandler {

    /**
     * Presents a standardized error message to the user.
     *
     * @param exception is the exception which caused the error
     */
    public void presentErrorMessage(Exception exception){
        StringBuilder builder = new StringBuilder();
        builder.append("ERROR: ");
        builder.append(exception.getMessage() + "\n");
        System.out.println(builder);
    }
}
