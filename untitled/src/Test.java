import A.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("all")
@SuppressWarnings("deprecation")
public class Test {

    public static void main(String[] args) {
        Locale locale = new Locale("fr", "fr");

        System.out.println(locale.getCountry());

        System.out.println(locale.getLanguage());

        System.out.println(locale.getDisplayLanguage());

        System.out.println(locale.getDisplayName());

        System.out.println(Thread.currentThread().getPriority());
    }
}
