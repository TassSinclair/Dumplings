package net.sinclairstudios.dumplings;

import net.sinclairstudios.dumplings.domain.*;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;

public class DumplingMatchers {

    public static Matcher<DumplingServings> servingsWithServings(final int servings) {
        return new CustomTypeSafeMatcher<DumplingServings>("Servings with servings " + servings) {
            @Override
            protected boolean matchesSafely(DumplingServings dumplingServings) {
                return dumplingServings.getServings() == servings;
            }
        };
    }

    public static Matcher<DumplingServingCalculation> calculationForDumpling(final Dumpling dumpling) {
        return new CustomTypeSafeMatcher<DumplingServingCalculation>("Calculation for " + dumpling) {
            @Override
            protected boolean matchesSafely(DumplingServingCalculation dumplingServingCalculation) {
                return dumplingServingCalculation.getDumpling() == dumpling;
            }
        };
    }

    public static Matcher<DumplingServingCalculation> calculationWithFraction(final Fraction servings) {
        return new CustomTypeSafeMatcher<DumplingServingCalculation>("Calculation with fraction " + servings) {
            @Override
            protected boolean matchesSafely(DumplingServingCalculation dumplingServingCalculation) {
                return dumplingServingCalculation.getServings().equals(servings);
            }
        };
    }

    public static Matcher<DumplingServings> orderFor(final Dumpling dumpling) {
        return new CustomTypeSafeMatcher<DumplingServings>("Order for " + dumpling) {
            @Override
            protected boolean matchesSafely(DumplingServings dumplingRating) {
                return dumplingRating.getDumpling() == dumpling;
            }
        };
    }

    public static Matcher<DumplingOrder> orderWithNameAndServings(final String name, final int servings) {
        return new CustomTypeSafeMatcher<DumplingOrder>("Dumpling " + name + " with " + servings + " servings") {
            @Override
            protected boolean matchesSafely(DumplingOrder dumplingOrder) {
                return dumplingOrder.getServings().getDumpling().getName().equals(name)
                        && dumplingOrder.getServings().getServings() == servings;
            }
        };
    }
}
