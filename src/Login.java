import java.util.Scanner;

public class Login {
    public static void main(String[] args) {

        //Metodien ja muuttujien alustus
        Scanner in = new Scanner(System.in);
        String enteredName;
        String foreName;
        String surName;
        String comDomain;


        //Pääohjelma alkaa
        System.out.println("Welcome! Tell us your name and company domain,\n" +
                "and we can generate email and username to you");
        System.out.println("(To close the program type \"exit\")\n ");

        System.out.print("Enter your forename: ");
        enteredName = in.nextLine();                                   //Pyydetään käyttäjää syöttämään etunimi
        foreName = nameValidation(enteredName, "forename");     //Kutsutaan metodi nimen tarkistusta varten

        System.out.print("Enter your surname: ");
        enteredName = in.nextLine();                                  //Pyydetään käyttäjää syöttämään etunimi
        surName = nameValidation(enteredName, "surname");      //Kutsutaan metodi nimen tarkistusta varten


        //Kysytään yrityksen domainia ja tarkistetaan onko se syötetty oikeassa muodossa
        System.out.print("Enter company domain (Example \"company.com)\": ");

        while (true) {
            comDomain = in.nextLine();

            int lastIndex = comDomain.lastIndexOf('.');  //Tarkistetaan onko stringissä pistettä

            if (comDomain.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (lastIndex != -1 && lastIndex < comDomain.length() - 1 &&     //Tarkistetaan, että domain on oikeas muodossa
                    lastIndex != 0 && comDomain.matches("[a-zA-Z.]+")) {
                break;
            }
            System.out.print("Enter valid domain(a-Z) (Example \"company.com)\"): ");
        }

        System.out.println("------------------------------------------------------------");
        generateEmail(foreName, surName, comDomain);    //Kutsutaan metodi ja luodaan sähköposti
        generateUserName(foreName, surName);            //Kutsutaan metodi ja luodaan käyttäjätunnus
        System.out.println("------------------------------------------------------------");
    }

    public static void generateEmail(String fore, String sur, String com) {
        //Muutetaan kirjaimet pieniksi ja tulostetaan sähköposti
        System.out.printf("Your email is: %s.%s@%s\n", fore.toLowerCase(), sur.toLowerCase(), com.toLowerCase());
    }

    public static void generateUserName(String fore, String sur) {

        fore = fore.toLowerCase();
        sur = sur.toLowerCase();
        int surLength = sur.length();

        //Luodaan käyttäjänimi riippuen siitä kuinka pitkiä etu/sukunimi on
        if (fore.length() < 4 && sur.length() < 4) {
            System.out.printf("Your username is: %s%s\n", fore, sur);
        } else if (fore.length() < 4) {
            System.out.printf("Your username is: %s%s\n", fore, sur.substring((surLength - 4)));
        } else if (sur.length() < 4) {
            System.out.printf("Your username is: %s%s\n", fore.substring(0, 4), sur);
        } else {
            System.out.printf("Your username is: %s%s\n", fore.substring(0, 4), sur.substring((surLength - 4)));
        }
    }

    public static String nameValidation(String input, String name) {
        Scanner in = new Scanner(System.in);

        while (true) {

            if (input.equalsIgnoreCase("exit")) {                      //Ohjelman lopetus jos,
                System.exit(0);                                             //käyttäjä kirjoittaa "exit"
            } else if (input.matches("[a-zA-Z]+") && input.length() > 1) {  //Tarkistetaan nimen pituus ja sisältämät merkit.
                return input;
            }
            System.out.printf("Enter valid %s(a-Z): ", name);

            input = in.nextLine();
        }
    }
}
