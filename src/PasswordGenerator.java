/*
* Copyright (c) 2024, 2024, Robert Hernandez. All rights reserved.
*/

import java.security.SecureRandom;

/*
* Password Generated class
* this class generates a password based on four levels of security
*
* In the first level a password will be generated with only lower case letters
* In the second level a password will be generated with lower case letters and numbers (0 to 9)
* In the third level a password will be generated with lower case letters, numbers and capital letters
* In the fourth level a password will be generated with lower case letters, numbers, capital letters and symbols
*/
public class PasswordGenerator {
    // the constants representing the security levels
    public final int FIRST_LEVEL = 1;
    public final int SECOND_LEVEL = 2;
    public final int THIRD_LEVEL = 3;
    public final int FOURTH_LEVEL = 4;

    // the arrays that have what the program requires
    private final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] numbers = "0123456789".toCharArray();
    private final char[] symbols = "!@#$%^&*()+`-?_=<>/|".toCharArray();

    // the variable that stores the password to be created
    private String password = "";

    // getter method of password variable
    public String getPassword (){
        return password;
    }

    /**
     * this functions generates the password
     * firstly, this function receives two parameters
     * @param size the length of the password desired by the user.
     * @param securityLevel the level of security that the password will have.
     * In the first place, the function checks if the size's values is equal to 0 and in
     * that case it will return an empty string.
     * In the other case, the function creates a StringBuilder object with length of the value of size
     * then a 2D char array is created with four rows:
     *      - the row 0 stores the letters array
     *      - the row 1 stores the numbers array
     *      - the row 2 stores the capitalLetters array
     *      - the row 3 stores the symbols array
     * then a variable calls indexLevel is created and stores the smaller value of two values: value of securityLevel and 4,
     *      and then takes that value subtracts 1
     *      this variable stores the index row according to the given security level
     *      for example, if the security level is 3, the program analyze the smaller number between 3 and 4 and
     *      subtracts 1 resulting in 3
     * then a SecureRandom object is created to generate a random number
     * then a while loop appears to check if the passwordBuilder.length() is less than size
     * and inside it, there is a for loop that runs if the iterator is less or equal to indexLevel and if
     * passwordBuilder.length() is less than size, in the event that the conditions are met,
     * it puts a random value according to the length of the column.
     * then the password is save into the local variable calls password
     * @return the function returns the generated password
     */
    public String generatePassword (int size, int securityLevel){
        if (size == 0){
            System.out.println("the password needs to have a size");
            return "";
        }

        StringBuilder passwordBuilder = new StringBuilder(size);
        char[][] charSets = {letters, numbers, capitalLetters, symbols};
        int indexLevel = Math.min(securityLevel, 4) - 1;
        SecureRandom random = new SecureRandom();

        while (passwordBuilder.length() < size){
            for (int i=0; i<=indexLevel && passwordBuilder.length()<size; i++){
                passwordBuilder.append(charSets[i][random.nextInt(charSets[i].length)]);
            }
        }

        password = passwordBuilder.toString();

        return password;
    }
}
