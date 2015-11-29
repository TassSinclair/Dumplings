package net.sinclairstudios.dumplings.calculation;

import junit.framework.TestCase;
import net.sinclairstudios.dumplings.domain.Dumpling;
import net.sinclairstudios.dumplings.domain.DumplingOrder;
import net.sinclairstudios.dumplings.domain.DumplingServings;

import java.util.Arrays;
import java.util.List;

import static net.sinclairstudios.dumplings.DumplingMatchers.orderWithNameAndServings;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class DumplingServingAccumulatorTest extends TestCase {

    public void testShouldAccumulateMultipleListsOfDumplingServingsAndReturnThem() {
        DumplingServingAccumulator accumulator = new DumplingServingAccumulator();
        DumplingServings servings1 = createServingsWithNameAndServings("1", 1);
        DumplingServings servings2 = createServingsWithNameAndServings("2", 2);
        DumplingServings servings3 = createServingsWithNameAndServings("3", 3);

        accumulator.add(Arrays.asList(servings1, servings2));
        accumulator.add(Arrays.asList(servings3));
        List<DumplingOrder> all = accumulator.getAll();

        assertThat(all, hasItems(
            orderWithNameAndServings("1", 1),
            orderWithNameAndServings("2", 2),
            orderWithNameAndServings("3", 3)));
    }

    public void testShouldDscardServingsWithZeroQuantity() {
        DumplingServingAccumulator accumulator = new DumplingServingAccumulator();
        DumplingServings servings1 = createServingsWithNameAndServings("1", 0);
        DumplingServings servings2 = createServingsWithNameAndServings("2", 0);

        accumulator.add(Arrays.asList(servings1));
        accumulator.add(Arrays.asList(servings2));
        List<DumplingOrder> all = accumulator.getAll();

        assertThat(all, is(empty()));
    }


    public void testShouldCombineServingsWithTheSameDumplingName() {
        DumplingServingAccumulator accumulator = new DumplingServingAccumulator();
        DumplingServings servings1 = createServingsWithNameAndServings("1", 1);
        DumplingServings servings2 = createServingsWithNameAndServings("2", 2);
        DumplingServings servings3 = createServingsWithNameAndServings("1", 3);
        DumplingServings servings4 = createServingsWithNameAndServings("2", 4);

        accumulator.add(Arrays.asList(servings1));
        accumulator.add(Arrays.asList(servings2, servings3, servings4));
        List<DumplingOrder> all = accumulator.getAll();

        assertThat(all, hasItems(
                orderWithNameAndServings("1", 4),
                orderWithNameAndServings("2", 6)
        ));
    }

    private DumplingServings createServingsWithNameAndServings(String dumplingName, int servings) {
        return new DumplingServings(new Dumpling(dumplingName), servings);
    }
}
