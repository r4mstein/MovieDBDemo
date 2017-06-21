package ua.r4mstein.moviedbdemo.utills;

public class MathManager {

    public static float getRating(float rating) {
        float res = rating;
        if (rating % 0.5f == 0) return res;

        float ostacha = rating % 1;
        int cile = (int) (rating - ostacha);

        if (ostacha < 0.5) {
            res = cile + 0.5f;
        } else if (ostacha > 0.5) {
            res = cile + 1;
        }

        return res;
    }
}
