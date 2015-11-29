package net.sinclairstudios.dumplings.calculation


import net.sinclairstudios.dumplings.domain.DumplingRating
import net.sinclairstudios.dumplings.domain.DumplingServingCalculation
import net.sinclairstudios.dumplings.domain.DumplingServings
import net.sinclairstudios.dumplings.domain.Fraction

public class DumplingRatingTransformer(private val equaliser : DumplingCalculationEqualiser) {

    public fun organise(dumplingRatings : List<DumplingRating>, howManyPeople : Int,
                        preferMultiplesOf : Int) : List<DumplingServings> {
        val servingMultiplier = Fraction(1, preferMultiplesOf)

        val calculations = transformRatingsToCalculations(dumplingRatings, howManyPeople, servingMultiplier)

        equaliser.equalise(calculations);

        return transformCalculationsToOrders(calculations, servingMultiplier)
    }

    private fun transformRatingsToCalculations(ratings: List<DumplingRating>, howManyPeople: Int,
                                               servingMultiplier: Fraction) : List<DumplingServingCalculation> {
        val totalRatings = Math.max(ratings.fold(0, {
            runningTotal, rating -> (runningTotal + rating.rating.value)
        }), 1)
        return ratings.map({
            rating -> DumplingServingCalculation(rating.dumpling,
                Fraction(rating.rating.value * howManyPeople, totalRatings) * servingMultiplier)
        });
    }

    private fun transformCalculationsToOrders(calculations : List<DumplingServingCalculation>,
                                              servingMultiplier : Fraction) : List<DumplingServings> {
        return calculations.map({
            calculation -> DumplingServings(calculation.dumpling,
                                   (calculation.servings / servingMultiplier).getAsInt())
        })
    }
}
