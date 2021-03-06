package se.kth.iv1350.saleProcess.view;

/**
 * This class handles the error messages presented to the user.
 */
 class ErrorMessageHandler {

    /**
     * Presents a standardized error message to the user.
     *
     * @param exception is the exception which caused the error
     */
     void presentErrorMessage(String exception){
        StringBuilder builder = new StringBuilder();
        builder.append("ERROR: ");
        builder.append(exception + "\n\n");
        System.out.println(builder);
    }
}
