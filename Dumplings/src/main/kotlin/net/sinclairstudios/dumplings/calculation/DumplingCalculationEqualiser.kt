package net.sinclairstudios.dumplings.calculation

import net.sinclairstudios.dumplings.domain.DumplingServingCalculation

public open class DumplingCalculationEqualiser {

    public open fun equalise(dumplingServingCalculations : List<DumplingServingCalculation>) {

        var withRemainders = filterCalculationsWithRemainders(dumplingServingCalculations)
        while (!withRemainders.isEmpty())
        {
            val remainder = findLowestRemainder(withRemainders).trimRemainder()
            findHighestRemainder(withRemainders).addServings(remainder)

            withRemainders = filterCalculationsWithRemainders(withRemainders)
        }
    }

    private fun filterCalculationsWithRemainders(calculations: List<DumplingServingCalculation>) :
            List<DumplingServingCalculation> {
        return calculations.filter({
            calculation -> calculation.servings.hasRemainder()
        });
    }

    private fun findLowestRemainder(calculations: List<DumplingServingCalculation>) : DumplingServingCalculation {
        return calculations.minBy({
            quantity -> quantity.servings.getRemainder()
        }) ?: calculations.first()
    }

    private fun findHighestRemainder(calculations: List<DumplingServingCalculation>) : DumplingServingCalculation {
        return calculations.maxBy({
            quantity -> quantity.servings.getRemainder()
        }) ?: calculations.first()
    }
}