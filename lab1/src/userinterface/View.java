package userinterface;

public class View {
    enum Message {

        first {
            public void action() {
                System.out.println("Enter please a dirictory");
            }
        },
        second {
            public void action() {
                System.out.println("It is not a dirictory, try again");
            }
        };

        public void action() {
            System.out.println("");
        }


    }

    public void getMessage(String string) {

        if (string == "first") {
            //  Message.first;
        }
    }

    public void message(String string) {
        switch (string) {
            case "first": {

                System.out.println("Enter please a dirictory");
                break;

            }
            case " second": {

                System.out.println("It is not a dirictory, try again");
                break;

            }

        }
    }
}
