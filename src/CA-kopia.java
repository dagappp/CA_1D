import java.util.Arrays;
import java.util.Random;

public class CA {

    int rule[] = new int[8];
    int caSpace = 200;

    int caArray[];
    int caArrayNext[];

    void setInitial() {
        Random rand = new Random();
        caArray = new int[caSpace];
        caArrayNext = new int[caSpace];

        for (int i = 0; i < caSpace; i++) {
            caArray[i] = rand.nextInt(2);
            caArrayNext[i] = 0;//brzegi na 0
        }

        caArray[caSpace / 2] = 1;
    }


    void dec2bin(int dec) {
        int bin[];
        bin = new int[8];

        int count = 7;

        while (dec != 0) {
            bin[count] = dec % 2;
            count--;

            dec /= 2;
        }

        rule = bin;
    }

    int bin2Dec(int[] bin, int len) {
        int dec = 0;

        for (int i = 0; i < len; i++) {
            dec += bin[i] * Math.pow(2, (len - i - 1));
        }

        return dec;
    }

    int[] simulate(int caArrayEx[]) {
        for (int i = 0; i < caSpace; i++) {
            caArrayNext[i] = 0;
        }

        int neighNum = 3;
        int neigh[] = new int[neighNum];

        for (int i = 1; i < caSpace - 1; i++) {
            neigh[0] = caArrayEx[i - 1];
            neigh[1] = caArrayEx[i];//srodkowy
            neigh[2] = caArrayEx[i + 1];

            caArrayNext[i] = rule[7 - bin2Dec(neigh, neighNum)];
        }

        for (int i = 0; i < caSpace; i++) {
            caArrayEx[i] = caArrayNext[i];
        }

        return caArrayEx;
    }


    void setRule90() {
        dec2bin(90);
    }

    void setRule41() {
        dec2bin(41);
    }

    void setRule18() {
        dec2bin(18);
    }

    void setRule75() {
        dec2bin(75);
    }

    void setRule190() {
        dec2bin(190);
    }

    void setRule30() {
        dec2bin(30);
    }

    void setRule60() {
        dec2bin(60);
    }

    void setRule250() {
        dec2bin(250);
    }

    void periodic(int[] caArray) {
        int caSpace = caArray.length;
        int[] temp = Arrays.copyOf(caArray, caSpace);

        for (int i = 0; i < caSpace; i++) {
            caArray[i] = 0;
        }

        for (int i = 0; i < caSpace; i++) {
            int left= temp[(i + caSpace - 1) % caSpace];
            int right = temp[(i + 1) % caSpace];

            caArray[i] = rule[7 - bin2Dec(new int[]{left, temp[i], right}, 3)];
        }
    }

    void absorbic() {
        Random rand = new Random();
        caArray = new int[caSpace];
        caArrayNext = new int[caSpace];

        for (int i = 0; i < caSpace; i++) {
            caArray[i] = rand.nextInt(2);
            caArrayNext[i] = 0; //brzegi 0
        }
    }





}
