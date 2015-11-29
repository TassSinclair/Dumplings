package net.sinclairstudios.dumplings.domain
import java.io.Serializable

public class DumplingServings(override var dumpling : Dumpling, var servings : Int) : Serializable, HasDumpling {

    public override fun toString() : String {
        return "${dumpling.toString()} ($servings servings)"
    }

    public override fun equals(other: Any?): Boolean {
        return other is DumplingServings
                && equals(other)
    }

    public fun equals(that: DumplingServings): Boolean {
        return this.dumpling.equals(that.dumpling) && this.servings == that.servings
    }
}
