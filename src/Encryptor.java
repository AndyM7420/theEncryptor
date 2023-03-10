public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        int count=0;
            for (int i = 0; i < letterBlock.length; i++) {
                for (int s = 0; s < letterBlock[0].length; s++) {
                   if(count<str.length()) {
                       letterBlock[i][s] = str.substring(count, count+1);
                       count++;
                   }else{
                       letterBlock[i][s]="A";
            }
        }
    }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String temp="";
        for(int i=0;i<letterBlock[0].length;i++){
            for (String[] strings : letterBlock) {
                temp += strings[i];
            }
        }

return temp;    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        String temp=message;
        String together="";
        int size=numRows*numCols;
        while (temp.length()>0) {
            String each = "";
            if (temp.length() < size) {
                fillBlock(temp);
                together += encryptBlock();
                temp="";
            } else {
                each = temp.substring(0, size);
                fillBlock(each);
                together += encryptBlock();
                temp = temp.substring(size);

            }
        }
        return together;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the ???encryption key??? that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        int times=encryptedMessage.length()/(numCols*numRows);
        String temp=encryptedMessage;
        String together = "";
        while (times>0) {
            decryptBlock(temp);
            encryptBlock();
            for (String[] strings : letterBlock) {
                for (int s = 0; s < letterBlock[0].length; s++) {
                    if(strings[s].equals("A")&&(times-1==0)){
                        together=together;
                    }else{
                    together += strings[s];
                }}
            }
            temp=temp.substring(numCols*numRows);
            times--;
        }
        return together;
        /* to be implemented in part (d) */
    }
    public void decryptBlock(String encryptor){
        int count=0;
        for (int i = 0; i < letterBlock[0].length; i++) {
            for (int s = 0; s < letterBlock.length; s++) {
                if(count<encryptor.length()) {
                    letterBlock[s][i] = encryptor.substring(count, count+1);
                    count++;
                }else{
                    letterBlock[s][i]="A";
                }
            }
        }
}}