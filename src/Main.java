public class Main {

    public static void main(String[] args) {
        try {
            DBConnector.setConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
