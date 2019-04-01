package ua.nure.timoshenko.practice7.util;

import ua.nure.timoshenko.practice7.entity.Medicine;
import ua.nure.timoshenko.practice7.entity.Medicines;

import java.util.Comparator;

/**
 * Contains static methods for sorting.
 */
public final class Sorter {
    private Sorter() {
    }

    public static  void sortMedicineByName(Medicines medicines) {
        medicines.getMedicine().sort(Comparator.comparing(Medicine::getName));
    }

    public static  void sortMedicineByVersionSize(Medicines medicines) {
        medicines.getMedicine().sort(Comparator.comparingInt((Medicine o) -> o.getVersions().size()));
    }

    public static  void sortMedicineByAnalog(Medicines medicines) {
            medicines.getMedicine().sort(Comparator.comparingInt((Medicine o) -> o.getAnalogs().size()));
    }


}