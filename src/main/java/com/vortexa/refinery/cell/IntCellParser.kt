package com.vortexa.refinery.cell

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import kotlin.math.round

class IntCellParser : CellParser<Int> {

    override fun tryParse(cell: Cell?): Int? {
        if (cell == null) return null
        return when {
            cell.cellType == CellType.NUMERIC && !DateUtil.isCellDateFormatted(cell) -> {
                val doubleValue = cell.numericCellValue
                if (doubleValue == round(doubleValue)) doubleValue.toInt() else null
            }
            cell.cellType == CellType.STRING -> cell.stringCellValue.trim().toIntOrNull()
            else -> null
        }
    }
}
