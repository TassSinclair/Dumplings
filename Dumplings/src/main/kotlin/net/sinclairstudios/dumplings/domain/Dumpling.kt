package net.sinclairstudios.dumplings.domain
import java.io.Serializable

public open class Dumpling(val name: String): Serializable {

    public override fun toString(): String {
        return "$name dumpling"
    }

    public override fun equals(other: Any?): Boolean {
        return other is Dumpling
            && equals(other)
    }

    public fun equals(that: Dumpling): Boolean {
        return this.name.equals(that.name)
    }
}
